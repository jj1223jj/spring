package com.java.board.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HomeAscpect;
import com.java.board.dao.BoardDao;
import com.java.board.dto.BoardDto;

@Component
public class BoardServiceImp implements BoardService {
	
	@Autowired
	private BoardDao boardDao;

	
	@Override
	public void boardWrite(ModelAndView mav) {
		//부모글, root글
		int boardNumber=0;			// 글번호 (DB에서 결정)
		int groupNumber=1;			// 그룹번호
		int sequenceNumber=0;		// 글순서
		int sequenceLevel=0;		// 글레벨
		
		Map<String,Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
			
		if(request.getParameter("boardNumber")!=null) {	//답글
			boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
			groupNumber=Integer.parseInt(request.getParameter("groupNumber"));
			sequenceNumber=Integer.parseInt(request.getParameter("sequenceNumber"));
			sequenceLevel=Integer.parseInt(request.getParameter("sequenceLevel"));
		}
		
		request.setAttribute("boardNumber", boardNumber);
		request.setAttribute("groupNumber", groupNumber);
		request.setAttribute("sequenceNumber", sequenceNumber);
		request.setAttribute("sequenceLevel", sequenceLevel);
		
		
		mav.setViewName("board/write");
	}

	@Override
	public void boardWriteOk(ModelAndView mav) {
		Map<String,Object> map = mav.getModelMap();
		
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		BoardDto boardDto = (BoardDto)map.get("boardDto");
		//System.out.println(boardDto.toString()); 				// Dto의 변수명과  BoardWrite input tag name속성과 같아야한다.
		HomeAscpect.logger.info(HomeAscpect.logMsg+boardDto.toString());
		
		boardWriteNumber(boardDto);
		
		boardDto.setWriteDate(new Date());
		boardDto.setReadCount(0);
		
		int check=boardDao.boardWriteOk(boardDto);
		HomeAscpect.logger.info(HomeAscpect.logMsg+check);
		
		mav.addObject("check",check);
		mav.setViewName("board/writeOk");
	}
	
	public void boardWriteNumber(BoardDto boardDto) {
		int boardNumber = boardDto.getBoardNumber();		// 0
		int groupNumber = boardDto.getGroupNumber();		// 1
		int sequenceNumber = boardDto.getSequenceNumber();	// 0
		int sequenceLevel = boardDto.getSequenceLevel();	// 0
		
		if(boardNumber==0) {		// ROOT - 그릅번호
			int max = boardDao.groupNumberMax();	//넘어가는 값이 없으면 생략
			HomeAscpect.logger.info(HomeAscpect.logMsg+max);
			
			if(max!=0) boardDto.setGroupNumber(max+1);
		}else {
			HashMap<String, Integer> map = new HashMap<String,Integer>();
			map.put("groupNumber", groupNumber);
			map.put("sequenceNumber",sequenceNumber);
			
			int check=boardDao.boardWirteNumber(map);
			HomeAscpect.logger.info(HomeAscpect.logMsg+check);
			
			boardDto.setSequenceNumber(sequenceNumber+1);
			boardDto.setSequenceLevel(sequenceLevel+1);
		}
			
	}

	@Override
	public void boardList(ModelAndView mav) {
		Map<String,Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber==null) pageNumber="1";
		
		int currentPage=Integer.parseInt(pageNumber);	// 요청페이지 -시작, 끝
		HomeAscpect.logger.info(HomeAscpect.logMsg+ "currentPage: " +currentPage);
		
		int count=boardDao.boardCount();
		HomeAscpect.logger.info(HomeAscpect.logMsg+ "count: " +count);
		
		
		int boardSize = 10;
		
		int startRow = (currentPage - 1) * boardSize + 1;
		int endRow = currentPage * boardSize;

		List<BoardDto> boardList = null;

		if (count > 0) {
			boardList = boardDao.BoardList(startRow, endRow);
			HomeAscpect.logger.info(HomeAscpect.logMsg+ "List size: " + boardList.size());
		}

		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);
		mav.addObject("boardList", boardList);
		
		mav.setViewName("board/list");
		
	}

	@Override
	public void boardRead(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		HomeAscpect.logger.info(HomeAscpect.logMsg+boardNumber+", "+pageNumber);
		
		BoardDto boardDto = boardDao.boardRead(boardNumber);
		HomeAscpect.logger.info(HomeAscpect.logMsg+boardDto.toString());
		
		mav.addObject("boardDto",boardDto);
		mav.addObject("pageNumber",pageNumber);
		
		mav.setViewName("board/read");
		
	}

	@Override
	public void boardDelete(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		HomeAscpect.logger.info(HomeAscpect.logMsg+boardNumber+", "+pageNumber);
		
		int check=boardDao.boardDelete(boardNumber);
		HomeAscpect.logger.info(HomeAscpect.logMsg+check);
		
		mav.addObject("check",check);
		mav.addObject("pageNumber",pageNumber);
		
		mav.setViewName("board/delete");
	}
	
	@Override
	public void boardUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		HomeAscpect.logger.info(HomeAscpect.logMsg+boardNumber+", "+pageNumber);
		
		BoardDto boardDto = boardDao.boardUpRead(boardNumber);
		HomeAscpect.logger.info(HomeAscpect.logMsg+boardDto.toString());
		
		mav.addObject("boardDto",boardDto);
		mav.addObject("pageNumber",pageNumber);
		
		mav.setViewName("board/update");
	}
	
	@Override
	public void boardUpdateOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		BoardDto boardDto = (BoardDto)map.get("boardDto");
		
	//	int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
	//	LogAspect.logger.info(LogAspect.logMsg+boardDto.toString()+","+pageNumber);
		
		int check = boardDao.boardUpdate(boardDto);
		HomeAscpect.logger.info(HomeAscpect.logMsg+check);
		
		mav.addObject("check",check);
	//	mav.addObject("pageNumber",pageNumber);
	
		mav.setViewName("board/updateOk");
		
	}

}













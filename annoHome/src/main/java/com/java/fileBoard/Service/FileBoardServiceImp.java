package com.java.fileBoard.Service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HomeAscpect;


import com.java.fileBoard.dao.FileBoardDao;
import com.java.fileBoard.dto.FileBoardDto;

@Component
public class FileBoardServiceImp implements FileBoardService {
	
	@Autowired
	private FileBoardDao fileBoardDao;
	
	@Override
	public void fileWrite(ModelAndView mav) {
		/* 12 controller에서 reuqest를 받기위해 Map형 변수 선언 */
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		/* 10 기존의  mybatis writeAction에서 복붙 */
		//부모글, root글
		int boardNumber=0;			// 글번호 (DB에서 결정)
		int groupNumber=1;			// 그룹번호
		int sequenceNumber=0;		// 글순서
		int sequenceLevel=0;		// 글레벨
		
			
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
		
		mav.setViewName("fileBoard/write");
		
	}

	/* 14 */
	@Override
	public void fileWriteOk(ModelAndView mav) {
		// TODO Auto-generated method stub
		Map<String, Object> map = mav.getModelMap();
		FileBoardDto fileBoardDto=(FileBoardDto)map.get("fileBoardDto");
		/*18-2*/
		MultipartHttpServletRequest request =(MultipartHttpServletRequest)map.get("request");
		
		/*15*/
		fileBoarWriteNumnber(fileBoardDto);
		/* 18-1*/
		fileBoardDto.setWriteDate(new Date());
		fileBoardDto.setReadCount(0);
		
		MultipartFile upFile = request.getFile("file");		// 요청한 것 중에 파일이 있는지 확인 
		long fileSize = upFile.getSize();
		
		if(fileSize!=0) {
			String fileName = Long.toString(System.currentTimeMillis())+"_"+upFile.getOriginalFilename(); 
			
			/* 19-1  파일 저장경로*/
			File path = new File("C:\\pds");
			path.mkdir();
			
			if(path.exists() && path.isDirectory()) {
				File file = new File(path, fileName);
				
				try {
					upFile.transferTo(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				fileBoardDto.setFileSize(fileSize);
				fileBoardDto.setFileName(fileName);
				/* 19-2  파일 저장경로*/
				fileBoardDto.setPath(file.getAbsolutePath());
			}
		}
		
		HomeAscpect.logger.info(HomeAscpect.logMsg + fileBoardDto.toString());
		/* pom.xml에서 파일업로드 라이브러리 설정  */
		
		/*20*/
		int check = fileBoardDao.fileBoardWrite(fileBoardDto);
		HomeAscpect.logger.info(HomeAscpect.logMsg + check);
		
		mav.addObject("check",check);
		mav.setViewName("fileBoard/writeOk");
	}
	
	/* 16 */
	public void fileBoarWriteNumnber(FileBoardDto fileBoardDto) {
		int boardNumber = fileBoardDto.getBoardNumber();		// 0
		int groupNumber = fileBoardDto.getGroupNumber();		// 1
		int sequenceNumber = fileBoardDto.getSequenceNumber();	// 0
		int sequenceLevel = fileBoardDto.getSequenceLevel();	// 0
		
		if(boardNumber==0) {		// ROOT - 그릅번호
			int max = fileBoardDao.groupNumberMax();	// 17 dao에 추가 
			HomeAscpect.logger.info(HomeAscpect.logMsg+max);
			
			if(max!=0) fileBoardDto.setGroupNumber(max+1);
		}else {
			/*
			 * HashMap<String, Integer> map = new HashMap<String,Integer>();
			 * map.put("groupNumber", groupNumber);
			 * map.put("sequenceNumber",sequenceNumber);
			 * 
			 * int check=fileBoardDao.fileBoarWriteNumnber(map);
			 * HomeAscpect.logger.info(HomeAscpect.logMsg+check);
			 * 
			 * fileBoardDto.setSequenceNumber(sequenceNumber+1);
			 * fileBoardDto.setSequenceLevel(sequenceLevel+1);
			 */
		}
			
	}

	@Override
	public void fileBoardList(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber==null) pageNumber="1";
		
		int currentPage=Integer.parseInt(pageNumber);	// 요청페이지 -시작, 끝

		int count=fileBoardDao.getCount();
		HomeAscpect.logger.info(HomeAscpect.logMsg+count);
		
		int boardSize = 10;
		int startRow = (currentPage-1)*boardSize+1;
		int endRow =currentPage*boardSize;
		
		HomeAscpect.logger.info(HomeAscpect.logMsg+ startRow + ", " + endRow);
		
		int start = count - startRow + 1;
		int end = count - endRow + 1;
		if(end<0) end = 0;
		
		HomeAscpect.logger.info(HomeAscpect.logMsg+"start: "+ start + ", end:" + end);
		
		List<FileBoardDto> boardList = null;
		
		if(count>0) {
			boardList=fileBoardDao.getBoardList(startRow,endRow);
			HomeAscpect.logger.info(HomeAscpect.logMsg+"List size: "+boardList.size());
		}
		
		request.setAttribute("boardSize", boardSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("count", count);
		request.setAttribute("boardList", boardList);
		
		mav.setViewName("fileBoard/list");
	}
	
	@Override
	public void fileBoardRead(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		HomeAscpect.logger.info(HomeAscpect.logMsg+"boradNumber: "+boardNumber+" pageNumber: "+pageNumber);
		
		FileBoardDto fileBoardDto = fileBoardDao.fileBoardread(boardNumber);
		HomeAscpect.logger.info(HomeAscpect.logMsg+fileBoardDto.toString());
		
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("boardDto", fileBoardDto);
		
		mav.setViewName("fileBoard/read");
	}
	
	@Override
	public void fileBoardDownload(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response =(HttpServletResponse)map.get("response");
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		
		FileBoardDto boardDto = fileBoardDao.fileBoardupdate(boardNumber);
		HomeAscpect.logger.info(HomeAscpect.logMsg+boardDto.toString());

		// 다운로드
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			
			int index=boardDto.getFileName().indexOf("_")+1;
			String dbName=boardDto.getFileName().substring(index);
			String fileName;
			
			fileName = new String(dbName.getBytes("utf-8"),"ISO-8859-1");
				
				
			//파일로드 대화창 출력
			response.setHeader("Content-Disposition","attachment;filename="+fileName);
			response.setContentType("application/octet-stream");
			response.setContentLength((int)boardDto.getFileSize());
		
			bis = new BufferedInputStream(new FileInputStream(boardDto.getPath()));
			bos = new BufferedOutputStream(response.getOutputStream());
				
			while(true) {
				int data = bis.read();
				if(data==-1) break;
				bos.write(data);
			}
			bos.flush();
			
			if(bos !=null) bos.close();
			if(bis !=null) bis.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}











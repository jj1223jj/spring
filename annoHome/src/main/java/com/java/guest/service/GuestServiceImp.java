package com.java.guest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HomeAscpect;
import com.java.guest.dao.GuestDao;
import com.java.guest.dto.GuestDto;


@Component
public class GuestServiceImp implements GuestService {
	
	@Autowired
	private GuestDao guestDao;

	@Override
	public void getWrite(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber==null) {
			pageNumber="1";
		}
		
		int count = guestDao.getCount();
		HomeAscpect.logger.info(HomeAscpect.logMsg+"count: "+count);
		
		int currentPage=Integer.parseInt(pageNumber);		//1) �슂泥��럹�씠吏� 1
		
		int boardSize = 3;									// 2) �럹�씠吏��떦 異쒕젰�븷 寃뚯떆臾� �닔
		int startRow = ((currentPage-1)*boardSize)+1;		// �떆�옉踰덊샇
		int endRow = ((currentPage)*boardSize);				// �걹踰덊샇
		
		int start = count - startRow + 1;
		int end = count - endRow + 1;
		if(end<0) end = 0;
	
		List<GuestDto> guestList=null;
		
		if (count > 0) {
			guestList = guestDao.guestList(startRow, endRow);
			HomeAscpect.logger.info(HomeAscpect.logMsg+"ListSize: "+ guestList.size());
		}
		request.setAttribute("guestList",guestList );
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("boardSize", boardSize);
		request.setAttribute("count", count);
		
		mav.addObject(request);
		mav.setViewName("guest/write");
		System.out.println("guestHomepage");
	}

	@Override
	public void getWriteOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		GuestDto guestDto = (GuestDto)map.get("guestDto");
		
		guestDto.setWriteDate(new Date());
		guestDto.setMessage(guestDto.getMessage().replace("\n\n", "<br/>"));
		
		int check = guestDao.insert(guestDto);
		HomeAscpect.logger.info(HomeAscpect.logMsg+"check: "+check);
		
		mav.addObject("check",check);
		mav.setViewName("guest/writeOk");
	}
	
	@Override
	public void getdelete(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		int num = Integer.parseInt(request.getParameter("num"));
		//�궘�젣�뻽�쓣 �븣 洹� �럹�씠吏�媛� �떎�떆 異쒕젰�릺寃� �븯湲� �쐞�빐(4�럹�씠吏��뿉�꽌 �궘�젣�떆 1�럹�씠吏�媛� �븘�땶 4�럹�씠吏�濡� 濡쒕뵫) - VIEW�떒�쑝濡� �꽆�뼱媛�
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		HomeAscpect.logger.info(HomeAscpect.logMsg+ "num" + num + "/" + "pageNumber" + pageNumber);
		
		int check = guestDao.delete(num);
		HomeAscpect.logger.info(HomeAscpect.logMsg+ "check:" + check);
		
		request.setAttribute("check", check);
		request.setAttribute("pageNumber", pageNumber);
		
		mav.addObject("request",request);
		mav.setViewName("guest/delete");
	}
	
	@Override
	public void getUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		

		int num = Integer.parseInt(request.getParameter("num"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		HomeAscpect.logger.info(HomeAscpect.logMsg+ num+", "+pageNumber);
		
		GuestDto guestDto = guestDao.update(num);
		HomeAscpect.logger.info(HomeAscpect.logMsg+guestDto.toString());
		
		mav.addObject("guestDto", guestDto);
		mav.addObject("pageNumber", pageNumber);
		
		mav.setViewName("guest/update");
	}
	
	@Override
	public void getUpdateOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		GuestDto guestDto = (GuestDto)map.get("guestDto");
		
		guestDto.setNum(Integer.parseInt(request.getParameter("num")));
		guestDto.setPassword(request.getParameter("password"));
		guestDto.setMessage(request.getParameter("message"));
		HomeAscpect.logger.info(HomeAscpect.logMsg+ guestDto.toString());
		
		
		int check = guestDao.updateOk(guestDto);
		HomeAscpect.logger.info(HomeAscpect.logMsg+"check: "+check);
	  
		mav.addObject("check", check);
		
		mav.setViewName("guest/updateOk");
		
	}
}









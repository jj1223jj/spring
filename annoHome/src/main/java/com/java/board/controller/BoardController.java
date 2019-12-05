package com.java.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.InternalResourceView;

import com.java.board.dto.BoardDto;
import com.java.board.service.BoardService;

/**
 * 작성자 : jihyeon
 * 작성일 : 2019/11/25
 * 설   명 : 
 * 	MVC: FrontController -- 		DispatcherServlet
 * 									설정(~.properties)			- bean.xml 
 * 									MAP							- SimpleUrlHandlerMapping
 * 																  : 웹 요청시 URL과 컨트를러의 맵핑을 일괄 정의
 * 									요청 Command(주소 - CLASS)		- MultiActionController(해당 주소의 /함수 찾음)
 * 									Command - return view page 	- InternalResourceView
 * 						RequestDispatcher forward사용 result view - InternalResourceViewResolver
 */

@Controller
public class BoardController{
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/board/write.do",method = RequestMethod.GET)
	public ModelAndView boardWrite(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);	
		mav.addObject("response",response);	
		
		boardService.boardWrite(mav);
		return mav;
	}
	
	@RequestMapping(value = "/board/writeOk.do",method = RequestMethod.POST)
	public ModelAndView writeOk(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("response",response);
		mav.addObject("boardDto",boardDto);
		
		boardService.boardWriteOk(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "/board/list.do",method = RequestMethod.GET )
	public ModelAndView boardList(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("OK2");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		
		boardService.boardList(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "/board/read.do",method = RequestMethod.GET)
	public ModelAndView boardRead(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		
		boardService.boardRead(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "/board/delete.do", method = RequestMethod.GET)
	public ModelAndView BoardDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();	
		mav.addObject("request",request);
		
		boardService.boardDelete(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "/board/update.do", method = RequestMethod.GET)
	public ModelAndView BoardUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();	
		mav.addObject("request",request);
		
		boardService.boardUpdate(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "/board/updateOk.do", method = RequestMethod.POST)
	public ModelAndView BoardUpdateOk(HttpServletRequest request, HttpServletResponse response, BoardDto boardDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("response", response);
		mav.addObject("boardDto", boardDto);

		boardService.boardUpdateOk(mav); 
		
		return mav;
		
		
		
	}
}





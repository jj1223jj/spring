package com.java.filboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.java.fileBoard.Service.FileBoardService;
import com.java.fileBoard.dto.FileBoardDto;

/* 2. controller생성하고,  MultiActionController 상속하기 */
@Controller
public class FileBoardController{
	// 7. FileBoardService 변수선언, 생성자만들기 -> context.xml
	@Autowired
	private FileBoardService fileBoardService;

	@RequestMapping(value = "/fileBoard/write.do",method = RequestMethod.GET)
	public ModelAndView fileBoardWrite(HttpServletRequest request, HttpServletResponse response) {
		/* 4. return void로 함수생성 뒤 콘솔창에 ok확인 -> index페이지 하나 만들기
		 * 	 web.xml에 가서<Listen>,<Servlet>에 param xml파일 추가
		 */
		//System.out.println("ok");
		
		/*7.*/
		ModelAndView mav = new ModelAndView();
		/* 11 - service에 request를 전달하기 위해 추가해준다. */
		mav.addObject("request",request);
		fileBoardService.fileWrite(mav);
		
		return mav;
	}
	
	/* 13 fileBoard-context.xml에서 추가한뒤  */
	@RequestMapping(value = "/fileBoard/writeOk.do",method = RequestMethod.POST)
	public ModelAndView fileBoardWriteOk(HttpServletRequest request, HttpServletResponse response, FileBoardDto fileBoardDto) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("fileBoardDto",fileBoardDto);
		fileBoardService.fileWriteOk(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "/fileBoard/list.do", method = RequestMethod.GET)
	public ModelAndView fileBoardList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		
		fileBoardService.fileBoardList(mav);
		return mav;
	}
	
	@RequestMapping(value = "/fileBoard/read.do", method = RequestMethod.GET)
	public ModelAndView fileBoardRead(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		
		fileBoardService.fileBoardRead(mav);
		
		return mav;
	}
	
	@RequestMapping(value = "/fileBoard/download.do", method = RequestMethod.GET)
	public void fileBoardDownload(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request",request);
		mav.addObject("response",response);
		
		fileBoardService.fileBoardDownload(mav);
	}
}

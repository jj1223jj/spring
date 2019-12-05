package com.java.fileBoard.Service;

import org.springframework.web.servlet.ModelAndView;

/* 5. service패키지 생성 */
public interface FileBoardService {
	public void fileWrite(ModelAndView mav);
	public void fileWriteOk(ModelAndView mav);
	public void fileBoardList(ModelAndView mav);
	public void fileBoardRead(ModelAndView mav);
	public void fileBoardDownload(ModelAndView mav);
}

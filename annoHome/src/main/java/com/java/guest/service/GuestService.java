package com.java.guest.service;

import org.springframework.web.servlet.ModelAndView;

public interface GuestService {
	public void getWrite(ModelAndView mav);
	public void getWriteOk(ModelAndView mav);
	public void getdelete(ModelAndView mav);
	public void getUpdate(ModelAndView mav);
	public void getUpdateOk(ModelAndView mav);
}

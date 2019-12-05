package com.java.guest.dao;

import java.util.ArrayList;
import java.util.List;

import com.java.guest.dto.GuestDto;

public interface GuestDao {
	public int insert(GuestDto guestDto);
	public int getCount();
	public List<GuestDto> guestList(int startRow, int endRow);
	public int delete(int num);
	public GuestDto update(int num);
	public int updateOk(GuestDto guestDto);
}

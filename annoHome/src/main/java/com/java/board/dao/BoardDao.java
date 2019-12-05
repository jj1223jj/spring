package com.java.board.dao;

import java.util.HashMap;
import java.util.List;

import com.java.board.dto.BoardDto;

public interface BoardDao {
	public int groupNumberMax();
	public int boardWriteOk(BoardDto boardDto);
	public int boardWirteNumber(HashMap<String, Integer> map);
	public int boardCount();
	public List<BoardDto> BoardList(int startRow, int endRow);
	public BoardDto boardRead(int boardNumber);
	public int boardDelete(int boardNumber);
	public BoardDto boardUpRead(int boardNumber);
	public int boardUpdate(BoardDto boardDto);
}

package com.java.fileBoard.dao;

import java.util.List;

import com.java.fileBoard.dto.FileBoardDto;

public interface FileBoardDao {
	public int groupNumberMax();
	public int fileBoardWrite(FileBoardDto fileBoardDto);
	public int getCount();
	public List<FileBoardDto>getBoardList(int startRow,int endRow);
	public FileBoardDto fileBoardread(int boardNumber);
	public FileBoardDto fileBoardupdate(int boardNumber);
	
}

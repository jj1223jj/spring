package com.java.fileBoard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.fileBoard.dto.FileBoardDto;

@Component
public class FileBoardDaoImp implements FileBoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;


	@Override
	public int groupNumberMax() {
		return sqlSessionTemplate.selectOne("dao.fileBoardMapper.fileBoardGroupNumberMax");
	}

	@Override
	public int fileBoardWrite(FileBoardDto fileBoardDto) {
		
		int check = 0;
		
		if(fileBoardDto.getFileName()!=null) {
			check = sqlSessionTemplate.insert("dao.fileBoardMapper.fileInsertDo",fileBoardDto);
		}else {
			check = sqlSessionTemplate.insert("dao.fileBoardMapper.fileInsert",fileBoardDto);
		}
		
		return check;
	}
	
	@Override
	public int getCount() {
		return sqlSessionTemplate.selectOne("dao.fileBoardMapper.fileBoardCount");
	}
	
	@Override
	public List<FileBoardDto> getBoardList(int startRow, int endRow) {
		Map<String, Integer> hMap = new HashMap<String,Integer>();
		hMap.put("startRow",startRow);
		hMap.put("endRow",endRow);
		return sqlSessionTemplate.selectList("dao.fileBoardMapper.fileBoardList",hMap);
	}
	
	@Override
	public FileBoardDto fileBoardread(int boardNumber) {
		sqlSessionTemplate.update("fileBoardReadUpdate",boardNumber);

		return sqlSessionTemplate.selectOne("fileBoardReadSelect",boardNumber);
	}
	
	@Override
	public FileBoardDto fileBoardupdate(int boardNumber) {
		return sqlSessionTemplate.selectOne("fileBoardReadSelect",boardNumber);
	}
	
}

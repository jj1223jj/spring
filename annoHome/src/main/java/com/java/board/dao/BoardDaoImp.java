package com.java.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.java.aop.HomeAscpect;
import com.java.board.dto.BoardDto;

@Component
public class BoardDaoImp implements BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	

	@Override
	public int groupNumberMax() {
		return sqlSession.selectOne("boardGroupNumberMax");
	}

	@Override
	public int boardWriteOk(BoardDto boardDto) {
		return sqlSession.insert("dao.BoardMapper.boardinsert",boardDto);
	}

	@Override
	public int boardWirteNumber(HashMap<String, Integer> map) {
		// TODO Auto-generated method stub
		return sqlSession.update("dao.BoardMapper.boardWriteNumber",map);
	}

	@Override
	public int boardCount() {
		return sqlSession.selectOne("dao.BoardMapper.boardCount");
	}

	@Override
	public List<BoardDto> BoardList(int startRow, int endRow) {
		Map<String,Integer> hMap = new HashMap<String, Integer>();
		hMap.put("startRow",startRow);
		hMap.put("endRow",endRow);
		
		return sqlSession.selectList("dao.BoardMapper.boardList",hMap);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)		// 설정에서 쓰는것이 가장 좋다(안될수도잇음)
	public BoardDto boardRead(int boardNumber) {
		BoardDto boardDto=null;
		// 조회수 증가
		int check = sqlSession.update("dao.BoardMapper.boardReadUp",boardNumber);
		HomeAscpect.logger.info(HomeAscpect.logMsg+ check);
		
		// 글번호 선택
		boardDto = sqlSession.selectOne("dao.BoardMapper.boardReadSelect",boardNumber);
		
		return boardDto;
	}

	@Override
	public int boardDelete(int boardNumber) {
		return sqlSession.delete("dao.BoardMapper.boardDel",boardNumber);
	}

	@Override
	public BoardDto boardUpRead(int boardNumber) {
		BoardDto boardDto=null;
		boardDto = sqlSession.selectOne("dao.BoardMapper.boardReadSelect",boardNumber);
		
		return boardDto;
	}
	
	@Override
	public int boardUpdate(BoardDto boardDto) {

		return sqlSession.update("dao.BoardMapper.boardUpdateOk",boardDto);
	}
	
	
}

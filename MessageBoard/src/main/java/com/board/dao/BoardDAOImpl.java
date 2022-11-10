package com.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardDTO;

// 특정 클래스를 DAO로 표시하여 해당 역할을 명확히 해주는 주석
@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	// boardMapper.xml namespace와 동일하게 설정 : <mapper namespace="com.board.mappers.board">
	private static String namespace = "com.board.mappers.board";
	
	// board 조회
	@Override
	public List<BoardDTO> list() throws Exception {
		return sqlSession.selectList(namespace + ".list");
	}
	
	@Override
	public Integer getMaxSeq() {
		return sqlSession.selectOne(namespace + ".maxSeq");
	}
	
	@Override
	public int register(BoardDTO dto) {
		return sqlSession.insert(namespace+".register", dto);
	}
	
	@Override
	public void updateReadCount(int seq) {
		sqlSession.update(namespace + ".updateReadCount", seq);
	}
	
	@Override
	public BoardDTO view(int seq) {
		return  sqlSession.selectOne(namespace + ".view", seq);
	}
	
	@Override
	public int delete(int seq) {
		return sqlSession.delete(namespace+".delete", seq);
	}
	
	@Override
	public int update(BoardDTO dto) {
		return sqlSession.delete(namespace+".update", dto);
	}
	
}

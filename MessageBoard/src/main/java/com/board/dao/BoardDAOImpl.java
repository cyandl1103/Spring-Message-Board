package com.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.CriteriaSearch;

// Ư�� Ŭ������ DAO�� ǥ���Ͽ� �ش� ������ ��Ȯ�� ���ִ� �ּ�
@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	// boardMapper.xml namespace�� �����ϰ� ���� : <mapper namespace="com.board.mappers.board">
	private static String namespace = "com.board.mappers.board";
	
	// board ��ȸ
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
		return sqlSession.delete(namespace + ".delete", seq);
	}
	
	@Override
	public int update(BoardDTO dto) {
		return sqlSession.delete(namespace + ".update", dto);
	}
	
	@Override
	public List<BoardDTO> search(String subject) throws Exception  {
		return sqlSession.selectList(namespace + ".search", subject);
	}
	
	@Override
	public List<BoardDTO> getListPaging(Criteria cri) {
		return sqlSession.selectList(namespace + ".pageList", cri);
	}
	
	@Override
	public int getTotal() {
		return sqlSession.selectOne(namespace + ".getTotal");
	}
	
	@Override
	public int getTotal(String subject) {
		return sqlSession.selectOne(namespace + ".getTotalSubject", subject);
	}
	
	@Override
	public List<BoardDTO> getSearchPaging(CriteriaSearch criSearch) throws Exception {
		return sqlSession.selectList(namespace + ".pageSearch", criSearch);
	}
}

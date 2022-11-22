package com.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.CriteriaSearch;
import com.board.domain.ReplyDTO;

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
	public Integer getRMaxSeq() {
		return sqlSession.selectOne(namespace + ".maxRSeq");
	}
	
	@Override
	public Integer getMaxRe_step(int rep) {
		return sqlSession.selectOne(namespace + ".maxReStep", rep);
	}
	
	@Override
	public int register(BoardDTO dto) {
		return sqlSession.insert(namespace+".register", dto);
	}
	
	@Override
	public int register(ReplyDTO dto) {
		return sqlSession.insert(namespace+".replyRegister", dto);
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
	
	@Override
	public List<ReplyDTO> replyList(int bseq) throws Exception {
		return sqlSession.selectList(namespace + ".replyList", bseq);
	}
	
	@Override
	public void updateRe_step(int rep, int re_step) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("rep", rep);
		map.put("re_step", re_step);
		sqlSession.update(namespace + ".updateReStep", map);
		
	}
	
	@Override
	public Integer getMaxRe_levelStep(Map<String, Integer> map) {
		return sqlSession.selectOne(namespace + ".maxReLevelStep", map);
	}
}

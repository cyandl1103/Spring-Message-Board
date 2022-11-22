package com.board.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.board.domain.*;

// 인터페이스
public interface BoardDAO {
	public List<BoardDTO> list() throws Exception;
	public Integer getMaxSeq();
	public int register(BoardDTO dto);
	public void updateReadCount(int seq);
	public BoardDTO view(int seq);
	public int delete(int seq);
	public int update(BoardDTO dto);
	public List<BoardDTO> search(String subject) throws Exception ;
	
	public List<BoardDTO> getListPaging(Criteria cri);
	
	public int getTotal();
	public int getTotal(String subject);
	
	public List<BoardDTO> getSearchPaging(CriteriaSearch criSearch) throws Exception;
	public int register(ReplyDTO dto);
	
	public List<ReplyDTO> replyList(int bseq) throws Exception;
	public Integer getRMaxSeq();
	public Integer getMaxRe_step(int rep);
	public Integer getMaxRe_levelStep(Map<String, Integer> map);
	
	public void updateRe_step(int rep, int re_step);
}

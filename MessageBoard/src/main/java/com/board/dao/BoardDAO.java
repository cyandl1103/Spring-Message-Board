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
	public Integer getMaxRseq();
	
	public void updateRe_step(int rep, int re_step);

	public Integer getParentRep(Integer parent_rseq);
	public Integer getParentRe_level(Integer parent_rseq);
	public Integer getMaxParentRe_step(Integer parent_rseq);
	public void updateParentChild(Integer parent_rseq);
	public Integer getParentRe_step(Integer parent_rseq);
	
	ReplyDTO reply(int rseq);
	
	public int deleteReply(int rseq);
	public void updateParentChildDelete(int rseq);
	public void updateRe_stepDelete(Integer rep, Integer re_step);
	
	public Integer getParentsLastChild(Integer parent_max_re_step, Integer parent_rseq);
	public int getChild(Integer child_rseq);

}

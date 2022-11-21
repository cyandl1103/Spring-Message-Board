package com.board.dao;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.board.domain.*;

// �������̽�
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
}

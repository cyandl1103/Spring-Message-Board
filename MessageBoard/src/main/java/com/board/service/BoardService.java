package com.board.service;

import java.util.List;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.CriteriaSearch;
import com.board.domain.ReplyDTO;

public interface BoardService {
	
	public List<BoardDTO> list() throws Exception;
	public int register(BoardDTO dto) throws Exception;
	public BoardDTO view(int seq);
	public int delete(int seq);
	public int update(BoardDTO dto);
	public List<BoardDTO> search(String subject) throws Exception ;
	
	public List<BoardDTO> getListPaging(Criteria cri);
	public int getTotal();
	public int getTotal(String subject);
	
	public List<BoardDTO> getSearchPaging(CriteriaSearch criSearch) throws Exception;
	
	public int register(ReplyDTO dto) throws Exception;
	public List<ReplyDTO> replyList(int bseq) throws Exception;
}

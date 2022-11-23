package com.board.service;

import java.util.List;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.CriteriaSearch;
import com.board.domain.ReplyDTO;

public interface BoardService {
	
	public List<BoardDTO> list() throws Exception;	// 게시글 목록
	public int register(BoardDTO dto) throws Exception; // 게시글 등록
	public BoardDTO view(int seq); // 게시글 조회
	public int delete(int seq); // 게시글 삭제
	public int update(BoardDTO dto); // 게시글 수정
	public List<BoardDTO> search(String subject) throws Exception ; // 게시글 검색
	
	public List<BoardDTO> getListPaging(Criteria cri); // 게시글 페이징 처리
	public int getTotal(); // 총 게시글의 수
	

	public List<BoardDTO> getSearchPaging(CriteriaSearch criSearch) throws Exception; // 검색된 게시글 페이징 처리
	public int getTotal(String subject); // 검색된 게시글의 수
	
	
	public int register(ReplyDTO dto) throws Exception; // 댓글 등록
	public List<ReplyDTO> replyList(int bseq) throws Exception; // 댓글 목록
	
	public int deleteReply(int rseq); // 댓글 삭제
}

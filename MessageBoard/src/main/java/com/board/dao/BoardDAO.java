package com.board.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.board.domain.*;

// 인터페이스
public interface BoardDAO {
	// 게시물
	public List<BoardDTO> list() throws Exception; // 게시물 목록
	public Integer getMaxSeq(); // 다음 게시물의 번호의 기준이 될 seq의 최댓값
	public int register(BoardDTO dto); // 게시물 등록
	public void updateReadCount(int seq); // 조회수 1 증가
	public BoardDTO view(int seq); // 게시물 조회
	public int delete(int seq); // 게시물 삭제
	public int update(BoardDTO dto); // 게시물 수정
	public List<BoardDTO> search(String subject) throws Exception ; // 게시물 검색
	
	public List<BoardDTO> getListPaging(Criteria cri); // 게시물 페이지 단위로 끊어서 가져옴
	
	public int getTotal(); // 총 게시물의 수
	public int getTotal(String subject); // 검색 기준 총 게시물의 수
	
	public List<BoardDTO> getSearchPaging(CriteriaSearch criSearch) throws Exception; // 검색 기준 게시물 페이지 단위로 끊어서 가져옴
	
	// 댓글
	public int register(ReplyDTO dto); // 댓글 등록
	
	public List<ReplyDTO> replyList(int bseq) throws Exception; // 게시물의 댓글 목록
	public Integer getMaxRseq(); // 다음 댓글의 번호의 기준이 될 rseq의 최댓값
	
	public void updateRe_step(int rep, int re_step); // 댓글 삽입 후 뒤에 있을 댓글들 순번 하나씩 뒤로 밀리게 수정
	
	// 댓글 추가하기 위한 작업
	public Integer getParentRep(Integer parent_rseq); // 부모의 rep - 댓글 그룹의 최상위 댓글
	public Integer getParentRe_level(Integer parent_rseq); // 부모의 깊이 - 자식은 + 1 하기 위함
	public Integer getMaxParentRe_step(Integer parent_rseq); // 부모의 마지막 자식의 re_step
	public void updateParentChild(Integer parent_rseq); // 댓글 등록 위해 부모의 child + 1 해줌
	
	public Integer getParentRe_step(Integer parent_rseq); // 부모의 순번 - 부모에 대댓글이 없을 때를 위함
	
	public Integer getParentsLastChild(Integer parent_max_re_step, Integer parent_rseq); // 부모의 마지막 자식 rseq
	public int getChild(Integer child_rseq); // 부모의 자식 수
	
	public ReplyDTO reply(int rseq); // rseq 기준으로 댓글 찾음
	
	public int deleteReply(int rseq); // 댓글 삭제
	public void updateParentChildDelete(int rseq); // 댓글 삭제 위해 부모이 child - 1 해줌
	public void updateRe_stepDelete(Integer rep, Integer re_step); // 댓글 삭제 위해 뒤에 있는 댓글들 순번 하나씩 당겨줌
	

}

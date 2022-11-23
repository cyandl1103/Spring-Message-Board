package com.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.board.dao.BoardDAO;
import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.CriteriaSearch;
import com.board.domain.ReplyDTO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO dao;
	
	@Override
	public List<BoardDTO> list() throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}
	
	@Override
	public int register(BoardDTO dto) throws Exception {
		// TODO Auto-generated method stub
		// 게시글 없을 때
		if(dao.getMaxSeq() == null) { 
			dto.setSeq(1);
		}
		// 게시글 있을 때
		else {
			dto.setSeq(dao.getMaxSeq() + 1);
		}
		
		return dao.register(dto);
	}
	
	@Override
	public int register(ReplyDTO dto) throws Exception {
		
		// 부모 존재할 때 => 대댓글일 때
		if(dto.getParent_rseq() != null) {
			dto.setRep(dao.getParentRep(dto.getParent_rseq())); // 부모의 rep과 동일하게 설정
			dto.setRe_level(dao.getParentRe_level(dto.getParent_rseq()) + 1); // 부모의 re_level보다 1 크게 설정
			
			// 부모에 대댓글이 존재할 때
			if(dao.getMaxParentRe_step(dto.getParent_rseq()) != null)
				dto.setRe_step(dao.getMaxParentRe_step(dto.getParent_rseq()) + 1); // 부모의 자식 중 가장 큰 re_step보다 1 크게 설정
			// 부모에 대댓글이 존재하지 않을 때
			else
				dto.setRe_step(dao.getParentRe_step(dto.getParent_rseq()) + 1); // 부모의 re_step보다 1 크게 설정
			
			dto.setChild(0); // 새 댓글이므로 자식은 0개로 설정
			dao.updateParentChild(dto.getParent_rseq()); // 부모의 자식 수 1 증가
			dao.updateRe_step(dto.getRep(), dto.getRe_step()); // 뒷번호의 re_step들 1씩 증가 시킴
			
			// 대댓글의 rseq 부여
			Integer rmax = dao.getRMaxSeq();
			dto.setRseq(rmax + 1);
		}
		// 댓글일 때
		else {
			// 새로운 댓글일 시 새로운 rseq, rep 부여
			Integer rmax = dao.getRMaxSeq();
			// 댓글이 하나도 없을 때
			if(rmax == null) { 
				dto.setRseq(1);
				dto.setRep(1);
			}
			// 댓글이 있을 때
			else {
				// 가장 큰 rseq에서 1을 더한 값을 새 게시글 rseq에 부여
				dto.setRseq(rmax + 1);
				dto.setRep(rmax + 1);
			}

			// 초기화
			dto.setRe_level(0);
			dto.setRe_step(0); 
			dto.setParent_rseq(0); 
			dto.setChild(0);
		}
		
		// insert
		return dao.register(dto);
	}
	
	@Override
	public BoardDTO view(int seq) {
		dao.updateReadCount(seq);
		return dao.view(seq);
	}
	
	@Override
	public int delete(int seq) {
		return dao.delete(seq);
	}
	
	@Override
	public int update(BoardDTO dto) {
		return dao.update(dto);
	}
	
	@Override
	public List<BoardDTO> search(String subject) throws Exception {
		return dao.search(subject);
	}
	
	@Override
	public List<BoardDTO> getListPaging(Criteria cri) {
		return dao.getListPaging(cri);
	}
	
	@Override
	public int getTotal() {
		return dao.getTotal();
	}
	
	@Override
	public int getTotal(String subject) {
		return dao.getTotal(subject);
	}
	
	@Override
	public List<BoardDTO> getSearchPaging(CriteriaSearch criSearch) throws Exception {
		return dao.getSearchPaging(criSearch);
	}
	
	@Override
	public List<ReplyDTO> replyList(int bseq) throws Exception {
		return dao.replyList(bseq);
	}
}

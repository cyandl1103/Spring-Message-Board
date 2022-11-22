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
		// 새로운 댓글일 시 새로운 rseq, rep 부여
		if (dto.getRep() == null) {
			Integer rmax = dao.getRMaxSeq();
			if(rmax == null) { 
				dto.setRseq(1);
				dto.setRep(1);
			}
			// 게시글 있을 때
			else {
				// 가장 큰 rseq에서 1을 더한 값을 새 게시글 rseq에 부여
				dto.setRseq(rmax + 1);
				dto.setRep(rmax + 1);
			}
		}
		
		// 대댓글 일 때 새로운 rseq만 부여
		else {
			Integer rmax = dao.getRMaxSeq();
			if(rmax == null) { 
				dto.setRseq(1);
			}
			// 게시글 있을 때
			else {
				// 가장 큰 rseq에서 1을 더한 값을 새 게시글 rseq에 부여
				dto.setRseq(rmax + 1);
			}
			
		}
		
		// 새 댓글일 때 re_level은 null이므로 0으로 설정
		if (dto.getRe_level() == null) dto.setRe_level(0);

		
		// re_step 구함
		// 대댓글이 없어 re_step이 null일 경우 re_step을 0으로 설정
		if (dao.getMaxRe_step(dto.getRep()) == null) dto.setRe_step(0);
		
		// 대댓글 있는 경우 
		else dao.updateRe_step(dto.getRep(), dto.getRe_step());

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

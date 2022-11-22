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
		// �Խñ� ���� ��
		if(dao.getMaxSeq() == null) { 
			dto.setSeq(1);
		}
		// �Խñ� ���� ��
		else {
			dto.setSeq(dao.getMaxSeq() + 1);
		}
		
		return dao.register(dto);
	}
	
	@Override
	public int register(ReplyDTO dto) throws Exception {
		// ���ο� ����� �� ���ο� rseq, rep �ο�
		if (dto.getRep() == null) {
			Integer rmax = dao.getRMaxSeq();
			if(rmax == null) { 
				dto.setRseq(1);
				dto.setRep(1);
			}
			// �Խñ� ���� ��
			else {
				// ���� ū rseq���� 1�� ���� ���� �� �Խñ� rseq�� �ο�
				dto.setRseq(rmax + 1);
				dto.setRep(rmax + 1);
			}
		}
		
		// ���� �� �� ���ο� rseq�� �ο�
		else {
			Integer rmax = dao.getRMaxSeq();
			if(rmax == null) { 
				dto.setRseq(1);
			}
			// �Խñ� ���� ��
			else {
				// ���� ū rseq���� 1�� ���� ���� �� �Խñ� rseq�� �ο�
				dto.setRseq(rmax + 1);
			}
			
		}
		
		// �� ����� �� re_level�� null�̹Ƿ� 0���� ����
		if (dto.getRe_level() == null) dto.setRe_level(0);

		
		// re_step ����
		// ������ ���� re_step�� null�� ��� re_step�� 0���� ����
		if (dao.getMaxRe_step(dto.getRep()) == null) dto.setRe_step(0);
		
		// ���� �ִ� ��� 
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

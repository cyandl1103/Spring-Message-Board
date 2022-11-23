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
		
		// �θ� ������ �� => ������ ��
		if(dto.getParent_rseq() != null) {
			dto.setRep(dao.getParentRep(dto.getParent_rseq())); // �θ��� rep�� �����ϰ� ����
			dto.setRe_level(dao.getParentRe_level(dto.getParent_rseq()) + 1); // �θ��� re_level���� 1 ũ�� ����
			
			// �θ� ������ ������ ��
			if(dao.getMaxParentRe_step(dto.getParent_rseq()) != null)
				dto.setRe_step(dao.getMaxParentRe_step(dto.getParent_rseq()) + 1); // �θ��� �ڽ� �� ���� ū re_step���� 1 ũ�� ����
			// �θ� ������ �������� ���� ��
			else
				dto.setRe_step(dao.getParentRe_step(dto.getParent_rseq()) + 1); // �θ��� re_step���� 1 ũ�� ����
			
			dto.setChild(0); // �� ����̹Ƿ� �ڽ��� 0���� ����
			dao.updateParentChild(dto.getParent_rseq()); // �θ��� �ڽ� �� 1 ����
			dao.updateRe_step(dto.getRep(), dto.getRe_step()); // �޹�ȣ�� re_step�� 1�� ���� ��Ŵ
			
			// ������ rseq �ο�
			Integer rmax = dao.getRMaxSeq();
			dto.setRseq(rmax + 1);
		}
		// ����� ��
		else {
			// ���ο� ����� �� ���ο� rseq, rep �ο�
			Integer rmax = dao.getRMaxSeq();
			// ����� �ϳ��� ���� ��
			if(rmax == null) { 
				dto.setRseq(1);
				dto.setRep(1);
			}
			// ����� ���� ��
			else {
				// ���� ū rseq���� 1�� ���� ���� �� �Խñ� rseq�� �ο�
				dto.setRseq(rmax + 1);
				dto.setRep(rmax + 1);
			}

			// �ʱ�ȭ
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

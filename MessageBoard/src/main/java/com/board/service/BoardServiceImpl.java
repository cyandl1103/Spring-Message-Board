package com.board.service;

import java.io.File;
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
	
	// �Խñ� ���
	@Override
	public List<BoardDTO> list() throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}
	
	// �Խñ� ���
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
	
	// �Խñ� ��ȸ
	@Override
	public BoardDTO view(int seq) {
		dao.updateReadCount(seq);
		return dao.view(seq);
	}
	
	// �Խñ� ����
	@Override
	public int delete(int seq) {

		// ������ ��� + ���ϸ�
        String filepath = "D:\\WorkspaceG\\Spring-Message-Board\\MessageBoard\\src\\main\\webapp\\resources\\storage\\";
        
		//������ �ִ��� Ȯ��
        String filename = dao.getFileName(seq);
        if(filename!= "") {
        	filepath += filename;
            File deleteFile = new File(filepath);
     
            // ������ �����ϴ��� üũ �����Ұ�� true, ��������������� false
            if(deleteFile.exists()) {  
                // ������ �����մϴ�.
                deleteFile.delete(); 
            }
        }

		
		return dao.delete(seq);
	}
	
	// �Խñ� ����
	@Override
	public int update(BoardDTO dto) {
		return dao.update(dto);
	}
	
	// �Խñ� �˻�
	@Override
	public List<BoardDTO> search(String subject) throws Exception {
		return dao.search(subject);
	}
	
	// �Խñ� ����¡ ó��
	@Override
	public List<BoardDTO> getListPaging(Criteria cri) {
		return dao.getListPaging(cri);
	}
	
	// �� �Խñ��� ��
	@Override
	public int getTotal() {
		return dao.getTotal();
	}
	
	// �˻��� �Խñ� ����¡ ó��
	@Override
	public int getTotal(String subject) {
		return dao.getTotal(subject);
	}
	
	// �˻��� �Խñ��� ��
	@Override
	public List<BoardDTO> getSearchPaging(CriteriaSearch criSearch) throws Exception {
		return dao.getSearchPaging(criSearch);
	}
	
	// ��� ���
	@Override
	public List<ReplyDTO> replyList(int bseq) throws Exception {
		return dao.replyList(bseq);
	}
	
	// ��� ���
	@Override
	public int register(ReplyDTO dto) throws Exception {
		
		// �θ� ������ �� => ������ ��
		if(dto.getParent_rseq() != null) {
			dto.setRep(dao.getParentRep(dto.getParent_rseq())); // �θ��� rep�� �����ϰ� ����
			dto.setRe_level(dao.getParentRe_level(dto.getParent_rseq()) + 1); // �θ��� re_level���� 1 ũ�� ����
			
			// �θ� ������ ������ ��
			if(dao.getMaxParentRe_step(dto.getParent_rseq()) != null) {
				
				// dto�� �θ��� ������ �ڽĿ� �ڽ��� �ִ��� Ȯ��
				Integer parent_rseq = dto.getParent_rseq(); // dto�� �θ� rseq
				Integer parent_max_re_step = dao.getMaxParentRe_step(parent_rseq);  // �θ��� ������ �ڽ��� re_step
				Integer child_rseq = dao.getParentsLastChild(parent_max_re_step, parent_rseq); // �θ��� ������ �ڽ��� rseq
				
				int child_count = 0; // �ڽ��� �ڽ��� �ڽ�.. ������ �ڽ� ��
				
				// ������ �ڽĿ� �ڽ��� �����ϸ�
				while(dao.getChild(child_rseq) != 0) {
					child_count += dao.getChild(child_rseq); // �ڽ��� �ڽ� ��
					
					// ���� ������ �ڽ��� Ȯ��
					parent_rseq = child_rseq; // ���� �ڽ��� �θ�� ����
					parent_max_re_step = dao.getMaxParentRe_step(parent_rseq);  // �θ��� ������ �ڽ��� re_step
					child_rseq = dao.getParentsLastChild(parent_max_re_step, parent_rseq); // ������ �ڽ� rseq
				}
				
				// �θ��� �ڽ� �� ���� ū re_step�� �������� child �� + 1�� ����
				dto.setRe_step(dao.getMaxParentRe_step(dto.getParent_rseq()) + child_count + 1); 
			}
			// �θ� ������ �������� ���� ��
			else
				dto.setRe_step(dao.getParentRe_step(dto.getParent_rseq()) + 1); // �θ��� re_step���� 1 ũ�� ����
			
			dto.setChild(0); // �� ����̹Ƿ� �ڽ��� 0���� ����
			dao.updateParentChild(dto.getParent_rseq()); // �θ��� �ڽ� �� 1 ����
			dao.updateRe_step(dto.getRep(), dto.getRe_step()); // �޹�ȣ�� re_step�� 1�� ���� ��Ŵ
			
			// ������ rseq �ο� - �ִ� rseq + 1
			Integer rmax = dao.getMaxRseq();
			dto.setRseq(rmax + 1);
		}
		// ����� ��
		else {
			// rseq �ִ�
			Integer rmax = dao.getMaxRseq();
			// ����� �ϳ��� ���� ��
			if(rmax == null) { 
				// ù��°�� ����
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
	
	// ��� ����
	@Override
	public int deleteReply(int rseq) {
		ReplyDTO dto = dao.reply(rseq);
		// �θ� ������ ��
		// �θ��� child�� 1 ���ҽ�Ŵ
		if (dto.getParent_rseq() != 0) {
			dao.updateParentChildDelete(dto.getParent_rseq());
		}
		
		// �����Ϸ��� ��� �ؿ� �ٸ� ������ ���� ��
		// �ش� ��� ����
		if(dto.getChild() == 0) {
			dao.deleteReply(rseq);
			dao.updateRe_stepDelete(dto.getRep(), dto.getRe_step());
			return 1;
		}
		
		// ���� ��
		else {
			return 0;
		}
	
	}
	
	
}

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
	
	// 게시글 목록
	@Override
	public List<BoardDTO> list() throws Exception {
		// TODO Auto-generated method stub
		return dao.list();
	}
	
	// 게시글 등록
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
	
	// 게시글 조회
	@Override
	public BoardDTO view(int seq) {
		dao.updateReadCount(seq);
		return dao.view(seq);
	}
	
	// 게시글 삭제
	@Override
	public int delete(int seq) {

		// 파일의 경로 + 파일명
        String filepath = "D:\\WorkspaceG\\Spring-Message-Board\\MessageBoard\\src\\main\\webapp\\resources\\storage\\";
        
		//파일이 있는지 확인
        String filename = dao.getFileName(seq);
        if(filename!= "") {
        	filepath += filename;
            File deleteFile = new File(filepath);
     
            // 파일이 존재하는지 체크 존재할경우 true, 존재하지않을경우 false
            if(deleteFile.exists()) {  
                // 파일을 삭제합니다.
                deleteFile.delete(); 
            }
        }

		
		return dao.delete(seq);
	}
	
	// 게시글 수정
	@Override
	public int update(BoardDTO dto) {
		return dao.update(dto);
	}
	
	// 게시글 검색
	@Override
	public List<BoardDTO> search(String subject) throws Exception {
		return dao.search(subject);
	}
	
	// 게시글 페이징 처리
	@Override
	public List<BoardDTO> getListPaging(Criteria cri) {
		return dao.getListPaging(cri);
	}
	
	// 총 게시글의 수
	@Override
	public int getTotal() {
		return dao.getTotal();
	}
	
	// 검색된 게시글 페이징 처리
	@Override
	public int getTotal(String subject) {
		return dao.getTotal(subject);
	}
	
	// 검색된 게시글의 수
	@Override
	public List<BoardDTO> getSearchPaging(CriteriaSearch criSearch) throws Exception {
		return dao.getSearchPaging(criSearch);
	}
	
	// 댓글 목록
	@Override
	public List<ReplyDTO> replyList(int bseq) throws Exception {
		return dao.replyList(bseq);
	}
	
	// 댓글 등록
	@Override
	public int register(ReplyDTO dto) throws Exception {
		
		// 부모 존재할 때 => 대댓글일 때
		if(dto.getParent_rseq() != null) {
			dto.setRep(dao.getParentRep(dto.getParent_rseq())); // 부모의 rep과 동일하게 설정
			dto.setRe_level(dao.getParentRe_level(dto.getParent_rseq()) + 1); // 부모의 re_level보다 1 크게 설정
			
			// 부모에 대댓글이 존재할 때
			if(dao.getMaxParentRe_step(dto.getParent_rseq()) != null) {
				
				// dto의 부모의 마지막 자식에 자식이 있는지 확인
				Integer parent_rseq = dto.getParent_rseq(); // dto의 부모 rseq
				Integer parent_max_re_step = dao.getMaxParentRe_step(parent_rseq);  // 부모의 마지막 자식의 re_step
				Integer child_rseq = dao.getParentsLastChild(parent_max_re_step, parent_rseq); // 부모의 마지막 자식의 rseq
				
				int child_count = 0; // 자식의 자식의 자식.. 누적된 자식 수
				
				// 마지막 자식에 자식이 존재하면
				while(dao.getChild(child_rseq) != 0) {
					child_count += dao.getChild(child_rseq); // 자식의 자식 수
					
					// 다음 깊이의 자식을 확인
					parent_rseq = child_rseq; // 기존 자식을 부모로 설정
					parent_max_re_step = dao.getMaxParentRe_step(parent_rseq);  // 부모의 마지막 자식의 re_step
					child_rseq = dao.getParentsLastChild(parent_max_re_step, parent_rseq); // 마지막 자식 rseq
				}
				
				// 부모의 자식 중 가장 큰 re_step을 기준으로 child 수 + 1로 설정
				dto.setRe_step(dao.getMaxParentRe_step(dto.getParent_rseq()) + child_count + 1); 
			}
			// 부모에 대댓글이 존재하지 않을 때
			else
				dto.setRe_step(dao.getParentRe_step(dto.getParent_rseq()) + 1); // 부모의 re_step보다 1 크게 설정
			
			dto.setChild(0); // 새 댓글이므로 자식은 0개로 설정
			dao.updateParentChild(dto.getParent_rseq()); // 부모의 자식 수 1 증가
			dao.updateRe_step(dto.getRep(), dto.getRe_step()); // 뒷번호의 re_step들 1씩 증가 시킴
			
			// 대댓글의 rseq 부여 - 최대 rseq + 1
			Integer rmax = dao.getMaxRseq();
			dto.setRseq(rmax + 1);
		}
		// 댓글일 때
		else {
			// rseq 최댓값
			Integer rmax = dao.getMaxRseq();
			// 댓글이 하나도 없을 때
			if(rmax == null) { 
				// 첫번째로 지정
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
	
	// 댓글 삭제
	@Override
	public int deleteReply(int rseq) {
		ReplyDTO dto = dao.reply(rseq);
		// 부모가 존재할 때
		// 부모의 child를 1 감소시킴
		if (dto.getParent_rseq() != 0) {
			dao.updateParentChildDelete(dto.getParent_rseq());
		}
		
		// 삭제하려는 댓글 밑에 다른 대댓글이 없을 때
		// 해당 댓글 삭제
		if(dto.getChild() == 0) {
			dao.deleteReply(rseq);
			dao.updateRe_stepDelete(dto.getRep(), dto.getRe_step());
			return 1;
		}
		
		// 있을 때
		else {
			return 0;
		}
	
	}
	
	
}

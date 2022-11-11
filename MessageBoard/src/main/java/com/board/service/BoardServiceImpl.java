package com.board.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.board.dao.BoardDAO;
import com.board.domain.BoardDTO;

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
	
}

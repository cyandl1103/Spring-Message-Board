package com.board.service;

import java.util.List;

import com.board.domain.BoardDTO;

public interface BoardService {
	
	public List<BoardDTO> list() throws Exception;
	public int register(BoardDTO dto) throws Exception;
	public BoardDTO view(int seq);
	public int delete(int seq);
	public int update(BoardDTO dto);
	public List<BoardDTO> search(String subject) throws Exception ;
		
}

package com.board.dao;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.board.domain.*;

// 인터페이스
public interface BoardDAO {
	public List<BoardDTO> list() throws Exception;
	public Integer getMaxSeq();
	public int register(BoardDTO dto);
	public void updateReadCount(int seq);
	public BoardDTO view(int seq);
	public int delete(int seq);
	public int update(BoardDTO dto);
	public List<BoardDTO> search(String subject) throws Exception ;
}

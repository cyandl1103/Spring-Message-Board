package com.board.service;

import java.util.List;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.CriteriaSearch;
import com.board.domain.ReplyDTO;

public interface BoardService {
	
	public List<BoardDTO> list() throws Exception;	// �Խñ� ���
	public int register(BoardDTO dto) throws Exception; // �Խñ� ���
	public BoardDTO view(int seq); // �Խñ� ��ȸ
	public int delete(int seq); // �Խñ� ����
	public int update(BoardDTO dto); // �Խñ� ����
	public List<BoardDTO> search(String subject) throws Exception ; // �Խñ� �˻�
	
	public List<BoardDTO> getListPaging(Criteria cri); // �Խñ� ����¡ ó��
	public int getTotal(); // �� �Խñ��� ��
	

	public List<BoardDTO> getSearchPaging(CriteriaSearch criSearch) throws Exception; // �˻��� �Խñ� ����¡ ó��
	public int getTotal(String subject); // �˻��� �Խñ��� ��
	
	
	public int register(ReplyDTO dto) throws Exception; // ��� ���
	public List<ReplyDTO> replyList(int bseq) throws Exception; // ��� ���
	
	public int deleteReply(int rseq); // ��� ����
}

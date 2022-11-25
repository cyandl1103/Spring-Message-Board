package com.board.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.board.domain.*;

// �������̽�
public interface BoardDAO {
	// �Խù�
	public List<BoardDTO> list() throws Exception; // �Խù� ���
	public Integer getMaxSeq(); // ���� �Խù��� ��ȣ�� ������ �� seq�� �ִ�
	public int register(BoardDTO dto); // �Խù� ���
	public void updateReadCount(int seq); // ��ȸ�� 1 ����
	public BoardDTO view(int seq); // �Խù� ��ȸ
	public int delete(int seq); // �Խù� ����
	public int update(BoardDTO dto); // �Խù� ����
	public List<BoardDTO> search(String subject) throws Exception ; // �Խù� �˻�
	
	public List<BoardDTO> getListPaging(Criteria cri); // �Խù� ������ ������ ��� ������
	
	public int getTotal(); // �� �Խù��� ��
	public int getTotal(String subject); // �˻� ���� �� �Խù��� ��
	
	public List<BoardDTO> getSearchPaging(CriteriaSearch criSearch) throws Exception; // �˻� ���� �Խù� ������ ������ ��� ������
	
	// ���
	public int register(ReplyDTO dto); // ��� ���
	
	public List<ReplyDTO> replyList(int bseq) throws Exception; // �Խù��� ��� ���
	public Integer getMaxRseq(); // ���� ����� ��ȣ�� ������ �� rseq�� �ִ�
	
	public void updateRe_step(int rep, int re_step); // ��� ���� �� �ڿ� ���� ��۵� ���� �ϳ��� �ڷ� �и��� ����
	
	// ��� �߰��ϱ� ���� �۾�
	public Integer getParentRep(Integer parent_rseq); // �θ��� rep - ��� �׷��� �ֻ��� ���
	public Integer getParentRe_level(Integer parent_rseq); // �θ��� ���� - �ڽ��� + 1 �ϱ� ����
	public Integer getMaxParentRe_step(Integer parent_rseq); // �θ��� ������ �ڽ��� re_step
	public void updateParentChild(Integer parent_rseq); // ��� ��� ���� �θ��� child + 1 ����
	
	public Integer getParentRe_step(Integer parent_rseq); // �θ��� ���� - �θ� ������ ���� ���� ����
	
	public Integer getParentsLastChild(Integer parent_max_re_step, Integer parent_rseq); // �θ��� ������ �ڽ� rseq
	public int getChild(Integer child_rseq); // �θ��� �ڽ� ��
	
	public ReplyDTO reply(int rseq); // rseq �������� ��� ã��
	
	public int deleteReply(int rseq); // ��� ����
	public void updateParentChildDelete(int rseq); // ��� ���� ���� �θ��� child - 1 ����
	public void updateRe_stepDelete(Integer rep, Integer re_step); // ��� ���� ���� �ڿ� �ִ� ��۵� ���� �ϳ��� �����
	

}

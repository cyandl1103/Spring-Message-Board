package com.board.domain;

public class CriteriaSearch extends Criteria{
	// �˻��� ���� ���� criteria���� �˻� ������ �� ������ �߰�
	private String subject;

	
	public CriteriaSearch(String subject) {
		super();
		this.subject = subject;
	}
	
	public CriteriaSearch(int pageNum, int amount, String subject) {
		super(pageNum, amount);
		this.subject = subject;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
}

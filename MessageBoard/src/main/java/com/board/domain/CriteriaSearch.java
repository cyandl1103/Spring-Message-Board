package com.board.domain;

public class CriteriaSearch extends Criteria{
	// 검색을 위해 기존 criteria에서 검색 기준이 될 제목을 추가
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

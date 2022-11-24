package com.board.domain;

// 페이징 기준
public class Criteria {
	// 현재 페이지
	private int pageNum;
	// 한 페이지 당 보여질 게시물의 수
	private int amount;
	private int skip;
	
	// 기본 생성자
	public Criteria() {
		this.pageNum = 1;
		this.amount = 20; // 한 페이지의 게시물 수
		this.skip = 0;
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum-1) * amount;
	}

	// getter, setter
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.skip = (pageNum-1) * this.amount;
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.skip = (this.pageNum-1) * amount;
		this.amount = amount;
	}
	
	
	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	
	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", amount=" + amount + "]";
	}
}

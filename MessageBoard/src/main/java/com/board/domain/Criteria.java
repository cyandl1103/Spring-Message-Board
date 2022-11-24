package com.board.domain;

// ����¡ ����
public class Criteria {
	// ���� ������
	private int pageNum;
	// �� ������ �� ������ �Խù��� ��
	private int amount;
	private int skip;
	
	// �⺻ ������
	public Criteria() {
		this.pageNum = 1;
		this.amount = 20; // �� �������� �Խù� ��
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

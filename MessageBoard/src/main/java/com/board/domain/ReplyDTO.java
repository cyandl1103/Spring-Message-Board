package com.board.domain;

public class ReplyDTO {
	
	// Entity
	Integer bseq; // �Խñ� ��ȣ
	Integer rseq; // ��� ��ȣ
	String content; // ��� ����
	String name; // ��� �ۼ���
	String reg_date; // ��� �����
	
	Integer re_step; // ���� ����
	Integer re_level; // �鿩���� ��
	
	/*
	��۴�۴�� 			rseq = 1, re_step = 0, re_level = 0
		�� ��۴�۴��		rseq = 1, re_step = 1, re_level = 1
		�� ��۴�۴��		rseq = 1, re_step = 2, re_level = 1		
			����۴�۴��	rseq = 1, re_step = 2, re_level = 2	
		����۴�۴��		rseq = 1, re_step = 3, re_level = 1			
	*/
	
	public Integer getBseq() {
		return bseq;
	}
	public void setBseq(Integer bseq) {
		this.bseq = bseq;
	}
	public Integer getRseq() {
		return rseq;
	}
	public void setRseq(Integer resq) {
		this.rseq = resq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	
	public Integer getRe_step() {
		return re_step;
	}
	public void setRe_step(Integer re_step) {
		this.re_step = re_step;
	}
	public Integer getRe_level() {
		return re_level;
	}
	public void setRe_level(Integer re_level) {
		this.re_level = re_level;
	}
	
	
	
}

package com.board.domain;

public class ReplyDTO {
	
	// Entity
	Integer bseq; // °Ô½Ã±Û ¹øÈ£
	Integer rseq; // ´ñ±Û ¹øÈ£
	String content; // ´ñ±Û ³»¿ë
	String name; // ´ñ±Û ÀÛ¼ºÀÚ
	String reg_date; // ´ñ±Û µî·ÏÀÏ
	
	Integer re_step; // ´ë´ñ±Û ¼ø¼­
	Integer re_level; // µé¿©¾²±â ¼ö
	
	/*
	´ñ±Û´ñ±Û´ñ±Û 			rseq = 1, re_step = 0, re_level = 0
		¤¤ ´ñ±Û´ñ±Û´ñ±Û		rseq = 1, re_step = 1, re_level = 1
		¤¤ ´ñ±Û´ñ±Û´ñ±Û		rseq = 1, re_step = 2, re_level = 1		
			¤¤´ñ±Û´ñ±Û´ñ±Û	rseq = 1, re_step = 2, re_level = 2	
		¤¤´ñ±Û´ñ±Û´ñ±Û		rseq = 1, re_step = 3, re_level = 1			
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

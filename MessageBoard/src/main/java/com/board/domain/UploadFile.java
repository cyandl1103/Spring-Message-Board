package com.board.domain;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
	// 파일 이름
	// 서버에는 원본 이름 + 날짜로 저장
	// 뷰로 넘길 때 날짜 제거 후 전달
	private String name; 
	private MultipartFile mpfile; // 파일
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getMpfile() {
		return mpfile;
	}
	public void setMpfile(MultipartFile mpfile) {
		this.mpfile = mpfile;
	}
	
	
}

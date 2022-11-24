package com.board.domain;

public class UserDTO {
	private String id; // 아이디
	private String pw; // 비밀번호
	private String name; // 이름
	private Integer admin; // 관리자 여부 1 or 0, default 0, 따로 조작 안하고 mapper에서 0으로 설정함.. 불필요
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAdmin() {
		return admin;
	}
	public void setAdmin(Integer admin) {
		this.admin = admin;
	}
	
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", admin=" + admin + "]";
	}
	
	
}

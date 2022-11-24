package com.board.service;

import javax.servlet.http.HttpSession;

import com.board.domain.ReplyDTO;
import com.board.domain.UserDTO;


public interface UserService {
	
	public String loginCheck(UserDTO dto, HttpSession session); // 로그인 정보 확인
	public void logout(HttpSession session); // 로그아웃
	public int userRegister(UserDTO dto); // 회원가입
	
	
}

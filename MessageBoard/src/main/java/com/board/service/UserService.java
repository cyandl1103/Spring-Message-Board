package com.board.service;

import javax.servlet.http.HttpSession;

import com.board.domain.ReplyDTO;
import com.board.domain.UserDTO;


public interface UserService {
	
	public String loginCheck(UserDTO dto, HttpSession session);
	public void logout(HttpSession session);
	public int userRegister(UserDTO dto);
	
	
}

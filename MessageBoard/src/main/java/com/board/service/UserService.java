package com.board.service;

import javax.servlet.http.HttpSession;

import com.board.domain.ReplyDTO;
import com.board.domain.UserDTO;


public interface UserService {
	
	public String loginCheck(UserDTO dto, HttpSession session); // �α��� ���� Ȯ��
	public void logout(HttpSession session); // �α׾ƿ�
	public int userRegister(UserDTO dto); // ȸ������
	
	
}

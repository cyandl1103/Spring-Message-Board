package com.board.dao;

import com.board.domain.ReplyDTO;
import com.board.domain.UserDTO;

public interface UserDAO {
	public String loginCheck(UserDTO dto); // �α��� ���� Ȯ��

	public int userRegister(UserDTO dto); // ȸ������
}

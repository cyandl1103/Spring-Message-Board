package com.board.dao;

import com.board.domain.ReplyDTO;
import com.board.domain.UserDTO;

public interface UserDAO {
	public String loginCheck(UserDTO dto); // 로그인 정보 확인

	public int userRegister(UserDTO dto); // 회원가입
}

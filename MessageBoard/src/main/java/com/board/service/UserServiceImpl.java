package com.board.service;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.board.dao.UserDAO;
import com.board.domain.ReplyDTO;
import com.board.domain.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO dao;
	
	
	@Override
	public String loginCheck(UserDTO dto, HttpSession session) {
		String name = dao.loginCheck(dto);
		
		if (name != null) {
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", name);
		}
		return name;
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate(); // 세션 초기화

	}

	@Override
	public int userRegister(UserDTO dto) {
		try {
			int result =dao.userRegister(dto);
			return result;
		}
		catch (Exception e) {
			return 0;
		}
	}
}

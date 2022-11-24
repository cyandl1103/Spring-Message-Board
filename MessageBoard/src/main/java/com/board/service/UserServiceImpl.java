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
	
	
	// 로그인 정보 확인
	@Override
	public String loginCheck(UserDTO dto, HttpSession session) {
		// dto의 이름 가져옴
		String name = dao.loginCheck(dto);
		
		// 존재하는 회원일 때
		if (name != null) {
			// 세션에 id, name 저장
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", name);
		}
		return name;
	}

	// 로그아웃
	@Override
	public void logout(HttpSession session) {
		session.invalidate(); // 세션 초기화

	}

	 // 회원가입
	@Override
	public int userRegister(UserDTO dto) {
		try {
			int result =dao.userRegister(dto);
			// 성공적으로 가입
			return result;
		}
		// 중복된 아이디가 존재하면 예외 발생하게 됨
		catch (Exception e) {
			return 0;
		}
	}
}

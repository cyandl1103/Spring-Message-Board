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
	
	
	// �α��� ���� Ȯ��
	@Override
	public String loginCheck(UserDTO dto, HttpSession session) {
		// dto�� �̸� ������
		String name = dao.loginCheck(dto);
		
		// �����ϴ� ȸ���� ��
		if (name != null) {
			// ���ǿ� id, name ����
			session.setAttribute("id", dto.getId());
			session.setAttribute("name", name);
		}
		return name;
	}

	// �α׾ƿ�
	@Override
	public void logout(HttpSession session) {
		session.invalidate(); // ���� �ʱ�ȭ

	}

	 // ȸ������
	@Override
	public int userRegister(UserDTO dto) {
		try {
			int result =dao.userRegister(dto);
			// ���������� ����
			return result;
		}
		// �ߺ��� ���̵� �����ϸ� ���� �߻��ϰ� ��
		catch (Exception e) {
			return 0;
		}
	}
}

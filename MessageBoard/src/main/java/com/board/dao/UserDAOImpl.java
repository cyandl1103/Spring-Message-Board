package com.board.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.board.domain.ReplyDTO;
import com.board.domain.UserDTO;


@Repository
public class UserDAOImpl implements UserDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	// boardMapper.xml namespace�� �����ϰ� ���� : <mapper namespace="com.board.mappers.board">
	private static String namespace = "com.board.mappers.user";
	
	// �α��� ���� Ȯ��
	@Override
	public String loginCheck(UserDTO dto) {
		return sqlSession.selectOne(namespace + ".loginCheck", dto);
	}
	
	 // ȸ������
	@Override
	public int userRegister(UserDTO dto) {
		return sqlSession.insert(namespace+".register", dto);
	}

}

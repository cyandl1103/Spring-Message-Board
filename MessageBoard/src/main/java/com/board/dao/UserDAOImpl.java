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
	
	// boardMapper.xml namespace와 동일하게 설정 : <mapper namespace="com.board.mappers.board">
	private static String namespace = "com.board.mappers.user";
	
	@Override
	public String loginCheck(UserDTO dto) {
		return sqlSession.selectOne(namespace + ".loginCheck", dto);
	}
	
	@Override
	public int userRegister(UserDTO dto) {
		return sqlSession.insert(namespace+".register", dto);
	}

}

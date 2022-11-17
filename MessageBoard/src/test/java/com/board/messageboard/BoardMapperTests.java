package com.board.messageboard;


import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.board.dao.BoardDAO;
import com.board.domain.Criteria;
 
//@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class BoardMapperTests {
	
	@Inject
    private DataSource ds;
	@Inject
	private BoardDAO dao;
	private final static Logger log = Logger.getGlobal();
    /* 게시판 목록(페이징 적용)테스트 */
	 @Test
	 public void testGetListPaging() throws Exception {
	     
	     Criteria cri = new Criteria();
	                      
	     List list = dao.getListPaging(cri);
	     
	     list.forEach(board -> log.info("" + board));
	 }
}

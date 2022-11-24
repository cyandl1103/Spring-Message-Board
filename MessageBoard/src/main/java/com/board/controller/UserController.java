package com.board.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.board.domain.ReplyDTO;
import com.board.domain.UserDTO;
import com.board.service.UserService;

@Controller
@RequestMapping(value="/user/*")
public class UserController {
	
	@Inject
	private UserService service;

	@RequestMapping(value = "/loginCheck", method = RequestMethod.POST)
	public ModelAndView userLogin(@ModelAttribute UserDTO dto, HttpSession session) {
		String name = service.loginCheck(dto, session);
		ModelAndView mav = new ModelAndView();
		
		// 로그인 성공 - 사용자가 존재할 때
		if(name != null) {
			mav.setViewName("redirect:/board/list");
		}
		// 로그인 실패
		else {
			mav.setViewName("redirect:/");
			mav.addObject("message","error"); // error임을 명시
		}
		
		return mav;
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView userLogout(ModelAndView mav, HttpSession session) {
		service.logout(session);
		mav.setViewName("redirect:/");
		mav.addObject("message", "logout");
		return mav;
	}
	
	
	@RequestMapping(value="/registerView")
	public String userRegisterView() {
		return "/board/userRegister";
	}
	
	@ResponseBody
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String userRegister(Locale locale, Model model, UserDTO dto) {
		System.out.println(dto.getName());
		int result = service.userRegister(dto);
		
		if(result == 1)
			return "Y";
		
		else
			return "N";
	}
	

}

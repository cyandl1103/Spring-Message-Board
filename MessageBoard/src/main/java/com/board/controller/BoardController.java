package com.board.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.BoardDTO;
import com.board.service.BoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/board/*")
public class BoardController {
	
	// client -> dto -> controller -> dto -> service -> dto -> repository -> domain(entity) -> db
	// 여기선 controller -> service
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) throws Exception {
		List<BoardDTO> list = service.list();
		// model.addAttribute(view에서의 변수 이름, controller에서의 변수 이름)
		model.addAttribute("list", list);
		
		return "/board/list";
	}
	
	@RequestMapping(value = "/registerView", method = RequestMethod.GET)
	public String regiView(Locale locale, Model model) throws Exception {
		return "/board/register";
	}
	
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Locale locale, Model model, BoardDTO dto) throws Exception {
		// 현재 시간
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		dto.setReg_date(format.format(date));
		
		if(service.register(dto) == 1) {
			//System.out.println("yes");
			return "Y";
		}
		
		else {
			//System.out.println("no");
			return "N";
		}
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public String view(Locale locale, Model model, HttpServletRequest request) throws Exception {
		BoardDTO dto = service.view(Integer.parseInt((String)request.getParameter("seq")));
		model.addAttribute("view", dto);
		return "/board/view";
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Locale locale, Model model, HttpServletRequest request) throws Exception {
		if(service.delete(Integer.parseInt((String)request.getParameter("seq"))) == 1) {
			//System.out.println("yes");
			return "Y";
		}
		
		else {
			//System.out.println("no");
			return "N";
		}
	}
	
	@RequestMapping(value = "/goUpdateView", method = RequestMethod.POST)
	public String updateView(Locale locale, Model model, HttpServletRequest request) throws Exception {
		BoardDTO dto = service.view(Integer.parseInt((String)request.getParameter("seq")));
		model.addAttribute("view", dto);
		return "/board/update";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Locale locale, Model model, BoardDTO dto) throws Exception {
		if(service.update(dto) == 1) {
			return "Y";
		}else {
			return "N";
		}
	}
	
	
	//@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Locale locale, Model model, @RequestParam String subject) throws Exception {
		List<BoardDTO> dto = service.search(subject);
		model.addAttribute("search", dto);
		return "/board/search";
	}
}

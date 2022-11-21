package com.board.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.CriteriaSearch;
import com.board.domain.PageMakerDTO;
import com.board.domain.ReplyDTO;
import com.board.service.BoardService;
import com.board.service.FileValidator;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


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
	
	@Autowired
	private FileValidator fileValidator;
	
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public String list(Locale locale, Model model) throws Exception {
//		List<BoardDTO> list = service.list();
//		// model.addAttribute(view에서의 변수 이름, controller에서의 변수 이름)
//		// 매핑해서 view 부분으로 넘김
//		model.addAttribute("list", list);
//		
//		return "/board/list";
//	}
	
	// 글쓰기로 이동
	@RequestMapping(value = "/registerView", method = RequestMethod.GET)
	public String regiView(Locale locale, Model model) throws Exception {
		return "/board/register";
	}
	
	// 새 글 등록
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
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Locale locale, Model model, HttpServletRequest request) throws Exception {
		BoardDTO dto = service.view(Integer.parseInt((String)request.getParameter("seq")));
		model.addAttribute("view", dto);
		
		int bseq = dto.getSeq();
		List<ReplyDTO> list = service.replyList(bseq);
		model.addAttribute("list", list);
		
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
	
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Locale locale, Model model, Criteria cri, @RequestParam String subject) throws Exception {
		
		cri = new CriteriaSearch(subject);
		CriteriaSearch criSearch = (CriteriaSearch)cri;

		List<BoardDTO> list = service.getSearchPaging(criSearch);
		model.addAttribute("search", list);
		
		int total = service.getTotal(subject); 
		  
		PageMakerDTO pageMake = new PageMakerDTO(cri,total); 
		model.addAttribute("pageMaker", pageMake);
		
		return "/board/search";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/searchPage", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String search(Locale locale, Model model,
			@RequestParam("subject") String subject, @RequestParam("pageNum") String pageNum, @RequestParam("amount") String amount) 
					throws Exception {
		
		Criteria cri = new Criteria(Integer.parseInt(pageNum), Integer.parseInt(amount));
		cri = new CriteriaSearch(Integer.parseInt(pageNum), Integer.parseInt(amount), subject);
		CriteriaSearch criSearch = (CriteriaSearch)cri;

		List<BoardDTO> list = service.getSearchPaging(criSearch);
		
		int total = service.getTotal(subject); 
		  
		PageMakerDTO pageMake = new PageMakerDTO(cri,total); 
		
		JsonObject resultJson = new JsonObject();
		
		JsonArray listJsonArray = new JsonArray();
		for(int i = 0; i < list.size(); i++) {
			JsonObject listJson = new JsonObject();
			listJson.addProperty("seq", list.get(i).getSeq());
			listJson.addProperty("subject", list.get(i).getSubject());
			listJson.addProperty("content", list.get(i).getContent());
			listJson.addProperty("name", list.get(i).getName());
			listJson.addProperty("reg_date", list.get(i).getReg_date());
			listJson.addProperty("readCount", list.get(i).getReadCount());
			
			listJsonArray.add(listJson);
		}
		
		resultJson.add("list", listJsonArray);
		
		
		JsonObject pageMakeJson = new JsonObject();
		pageMakeJson.addProperty("startPage", pageMake.getStartPage());
		pageMakeJson.addProperty("endPage", pageMake.getEndPage());
		pageMakeJson.addProperty("prev", pageMake.isPrev());
		pageMakeJson.addProperty("next", pageMake.isNext());
		pageMakeJson.addProperty("total", pageMake.getTotal());
		pageMakeJson.addProperty("pageNum", pageMake.getCri().getPageNum());
		pageMakeJson.addProperty("amount", pageMake.getCri().getAmount());
		
		resultJson.add("pageMake", pageMakeJson);
		String resultString = resultJson.toString();

		return resultString;
	}
	
	
	
	  
	  @RequestMapping(value = "/list", method = RequestMethod.GET) 
	  public String list(Locale Locale, Criteria cri, Model model) throws Exception {
		  List<BoardDTO> list = service.getListPaging(cri); //
		  //model.addAttribute(view에서의 변수 이름, controller에서의 변수 이름) // 매핑해서 view 부분으로 넘김
		  model.addAttribute("list", list);
		  
		  int total = service.getTotal(); 
		  
		  PageMakerDTO pageMake = new PageMakerDTO(cri,total); 
		  model.addAttribute("pageMaker", pageMake);
		  return "/board/list"; 
	  }
	 
	
	@ResponseBody
	@RequestMapping(value = "/listPage", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	public String list(@RequestParam("pageNum") String pageNum, @RequestParam("amount") String amount, 
			 Locale locale, Model model) throws Exception {
		
		Criteria cri = new Criteria(Integer.parseInt(pageNum), Integer.parseInt(amount));
		List<BoardDTO> list = service.getListPaging(cri);
		int total = service.getTotal();
		
		PageMakerDTO pageMake = new PageMakerDTO(cri, total);
		
		JsonObject resultJson = new JsonObject();
		
		JsonArray listJsonArray = new JsonArray();
		for(int i = 0; i < list.size(); i++) {
			JsonObject listJson = new JsonObject();
			listJson.addProperty("seq", list.get(i).getSeq());
			listJson.addProperty("subject", list.get(i).getSubject());
			listJson.addProperty("content", list.get(i).getContent());
			listJson.addProperty("name", list.get(i).getName());
			listJson.addProperty("reg_date", list.get(i).getReg_date());
			listJson.addProperty("readCount", list.get(i).getReadCount());
			
			listJsonArray.add(listJson);
		}
		
		resultJson.add("list", listJsonArray);
		
		
		JsonObject pageMakeJson = new JsonObject();
		pageMakeJson.addProperty("startPage", pageMake.getStartPage());
		pageMakeJson.addProperty("endPage", pageMake.getEndPage());
		pageMakeJson.addProperty("prev", pageMake.isPrev());
		pageMakeJson.addProperty("next", pageMake.isNext());
		pageMakeJson.addProperty("total", pageMake.getTotal());
		pageMakeJson.addProperty("pageNum", pageMake.getCri().getPageNum());
		pageMakeJson.addProperty("amount", pageMake.getCri().getAmount());
		
		resultJson.add("pageMake", pageMakeJson);
		String resultString = resultJson.toString();

		return resultString;
	}
	
	
	// 댓글 등록
	@ResponseBody
	@RequestMapping(value = "/reply/register", method = RequestMethod.POST)
	public String replyRegister(Locale locale, Model model, ReplyDTO dto) throws Exception {
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
}

package com.board.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.CriteriaSearch;
import com.board.domain.PageMakerDTO;
import com.board.domain.ReplyDTO;
import com.board.domain.UploadFile;
import com.board.service.BoardService;
import com.board.validate.FileValidator;
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
	// ���⼱ controller -> service
	@Inject
	private BoardService service;
	
	@Autowired
	private FileValidator fileValidator;
	
	
	// �Խñ� ���
	// ù ȭ��
	@RequestMapping(value = "/list", method = RequestMethod.GET) 
	public String list(Locale Locale, Criteria cri, Model model, HttpSession session) throws Exception {
		
		// ���� �α��� ���� - �̸�
		Object obj_name = session.getAttribute("name");
		String name = (String)obj_name;
		model.addAttribute("userName", name);
		
		List<BoardDTO> list = service.getListPaging(cri); //
		//model.addAttribute(view������ ���� �̸�, controller������ ���� �̸�) // �����ؼ� view �κ����� �ѱ�
		model.addAttribute("list", list);
	  
		int total = service.getTotal(); 
	  
		PageMakerDTO pageMake = new PageMakerDTO(cri,total); 
		model.addAttribute("pageMaker", pageMake);
		return "/board/list"; 
	 }
	 
	// �Խñ� ���
	// ����¡ �� ȣ�� ��
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
	
	
	// �۾��� �������� �̵�
	@RequestMapping(value = "/registerView", method = RequestMethod.GET)
	public String regiView(Locale locale, Model model, HttpSession session) throws Exception {
		// ����� ����
		Object obj_name = session.getAttribute("name");
		String name = (String)obj_name;
		model.addAttribute("userName", name);
		
		return "/board/register";
	}
	
	// �� �� ���
	@ResponseBody
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Locale locale, Model model, BoardDTO dto, HttpSession session) throws Exception {
		
		// ����� ����
		Object obj_name = session.getAttribute("name");
		String name = (String)obj_name;
		model.addAttribute("userName", name);
		
		if(name != null) {
			dto.setName(name);
		}
		
		// ���� �ð�
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
	
	// ���� ���ε�
	@ResponseBody
	@RequestMapping(value ="/upload", method = RequestMethod.POST)
	public String fileUpload(@RequestParam("file") MultipartFile multi) {
		
		System.out.println(multi.getOriginalFilename());
		UploadFile uploadFile = new UploadFile();
		
		uploadFile.setMpfile(multi);
		uploadFile.setName(multi.getOriginalFilename());
		
		// validator doesnt work at the moment
//		fileValidator.validate(uploadFile, result);
//		if(result.hasErrors()) {
//			return null;
//		}
		
		MultipartFile file = uploadFile.getMpfile();
		String name = file.getOriginalFilename();
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		name += format.format(date);
		
		
		UploadFile fileObj = new UploadFile();
		fileObj.setName(name);
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		try {
			inputStream = file.getInputStream();
			String path = "D:\\WorkspaceG\\Spring-Message-Board\\MessageBoard\\src\\main\\webapp\\resources\\storage";
			//String path = WebUtils.getRealPath(request.getSession().getServletContext(), "/resources/storage");
			System.out.println("���ε� ���� ��� : " + path);
			
			File storage = new File(path);
			
			// ��ο� ������ ���� ��� ���� ����
			if(!storage.exists()) {
				storage.mkdir();
			}
			
			File newFile = new File(path+"/"+name);
				// ���� ����
	           if(!newFile.exists()) {
	               newFile.createNewFile();
	           }
	           
	           outputStream = new FileOutputStream(newFile);
	           
	           int read = 0 ; 
	           // ������ ũ��
	           byte[] b = new byte[(int)file.getSize()];
	           
	      while((read=inputStream.read(b)) != -1) {
	    	// ���� �ۼ�
	        outputStream.write(b,0,read);
	    }
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		//model.addAttribute("fileObj", fileObj);
		
		return name;
	}
	
	// ���� �ٿ�ε�
	@ResponseBody
	@RequestMapping(value = "/download")
	public byte[] fileDownload(HttpServletResponse response, @RequestParam("file") String name) {
		
		byte[] down = null;
		
		try {
			String path = "D:\\WorkspaceG\\Spring-Message-Board\\MessageBoard\\src\\main\\webapp\\resources\\storage";
			//String path = WebUtils.getRealPath(request.getSession().getServletContext(), "resources/storage");
			File file = new File(path + "/" + name);
			
			down = FileCopyUtils.copyToByteArray(file);
			String filename = new String(file.getName().getBytes(), "8859_1");
			filename = filename.substring(0, filename.length()-14);
			
			response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			response.setContentLength(down.length);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return down;
	}
	
	
	// �Խñ� ����
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
	
	// �Խñ� ���� �������� �̵�
	@RequestMapping(value = "/goUpdateView", method = RequestMethod.POST)
	public String updateView(Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception {
		// ����� ����
		Object obj_name = session.getAttribute("name");
		String name = (String)obj_name;
		model.addAttribute("userName", name);
		
		BoardDTO dto = service.view(Integer.parseInt((String)request.getParameter("seq")));
		model.addAttribute("view", dto);
		return "/board/update";
	}
	
	// �Խñ� ����
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Locale locale, Model model, BoardDTO dto) throws Exception {
		if(service.update(dto) == 1) {
			return "Y";
		}else {
			return "N";
		}
	}
	
	// �Խñ� �˻� - ���� ����
	// ù ȭ��
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Locale locale, Model model, Criteria cri, @RequestParam String subject, HttpSession session) throws Exception {
		
		// ����� ����
		Object obj_name = session.getAttribute("name");
		String name = (String)obj_name;
		model.addAttribute("userName", name);
		
		cri = new CriteriaSearch(subject);
		CriteriaSearch criSearch = (CriteriaSearch)cri;

		List<BoardDTO> list = service.getSearchPaging(criSearch);
		model.addAttribute("search", list);
		
		int total = service.getTotal(subject); 
		  
		PageMakerDTO pageMake = new PageMakerDTO(cri,total); 
		model.addAttribute("pageMaker", pageMake);
		
		return "/board/search";
	}
	
	// �Խñ� �˻� 
	// ����¡ �� ȣ�� ��
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
	 
	
	
	// �Խñ� ���� + ��� ����
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Locale locale, Model model, HttpServletRequest request, HttpSession session) throws Exception {
		// ����� ����
		Object obj_name = session.getAttribute("name");
		String name = (String)obj_name;
		model.addAttribute("userName", name);
		
		// �Խñ�
		// readcount + 1 �ϰ� �� dto�� ����
		BoardDTO dto = service.view(Integer.parseInt((String)request.getParameter("seq")));
		model.addAttribute("view", dto);
		
		String file_name = null;
		// ���� ���� �̸�
		if(dto.getFile().length() > 0) {
			file_name = dto.getFile().substring(0, dto.getFile().length()-14);	
		}
		
		model.addAttribute("file_name", file_name);

		// ��� ���
		// bseq = �Խñ� ��ȣ = �Խñ��� seq
		int bseq = dto.getSeq();
		List<ReplyDTO> list = service.replyList(bseq);
		model.addAttribute("list", list);
		
		return "/board/view";
		
	}
	
	// ��� ���
	@ResponseBody
	@RequestMapping(value = "/reply/register", method = RequestMethod.POST)
	public String replyRegister(Locale locale, Model model, ReplyDTO dto, HttpSession session) throws Exception {
		
		// ����� ����
		Object obj_name = session.getAttribute("name");
		String name = (String)obj_name;
		model.addAttribute("userName", name);
		
		// ���� �ð�
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		dto.setReg_date(format.format(date));
		
		if(service.register(dto) == 1) {
			return "Y";
		}
		
		else {
			return "N";
		}
	}

	// ��� ����
	// �̿ϼ�
	@ResponseBody
	@RequestMapping(value = "/reply/delete", method = RequestMethod.POST)
	public String replyDelete(Locale locale, Model model, HttpServletRequest request) throws Exception {
		
		if(service.deleteReply(Integer.parseInt((String)request.getParameter("rseq"))) == 1) {
			return "Y";
		}
		
		else {
			return "N";
		}
	}
}	


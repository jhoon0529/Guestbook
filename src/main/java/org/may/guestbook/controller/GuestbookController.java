package org.may.guestbook.controller;

import org.may.guestbook.dto.GuestbookDTO;
import org.may.guestbook.dto.PageRequestDTO;
import org.may.guestbook.service.GuestbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
@Log4j2
public class GuestbookController {
	
	private final GuestbookService service;
	
//	@GetMapping({"/","/list"})
	public  String list() {
		log.info(">>> List Run Success...");
		return "guestbook/list";
	}
	//전체 조회
	@GetMapping({"/","/list"})
	public void list(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
							Model model) {
		log.info(">>> List Run Success..."+pageRequestDTO);
		//PageResultDTO
		model.addAttribute("result", service.getList(pageRequestDTO));
	}
	
	//등록페이지 출력
	@GetMapping("/register")
	public void resterGet() {
		log.info(">>> Register View Page ");
	}
	
	//등록 - Modal No제공 - list페이지 리턴
	@PostMapping("/register")
	public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes) {
		log.info(">>> Register dto : " +dto);
		
		//register 실행 후 생성된 dtoToEntity를 통해 생성된 Entity 번호
		Long gno = service.register(dto);
		
		//post로 값을 넘겨줌.
		redirectAttributes.addFlashAttribute("msg",gno);
		
		return "redirect:/guestbook/list";
	}
	
	//단일 조회
	@GetMapping("/read")
	public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
							Model model) {
		log.info(">>> Read gno :" + gno);
		
		GuestbookDTO dto = service.read(gno);
		model.addAttribute("dto",dto);
	}

}

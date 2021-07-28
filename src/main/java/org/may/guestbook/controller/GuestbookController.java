package org.may.guestbook.controller;

import org.may.guestbook.dto.PageRequestDTO;
import org.may.guestbook.service.GuestbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/guestbook")
@RequiredArgsConstructor
@Log4j2
public class GuestbookController {
	
	private final GuestbookService service;
	
	@GetMapping({"/","/list"})
	public  void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info(">>> List Run Success..."+pageRequestDTO);
		
		model.addAttribute("result", service.getList(pageRequestDTO));
		
		
//		return "guestbook/list";
	}

}

package org.may.guestbook.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.may.guestbook.dto.GuestbookDTO;
import org.may.guestbook.dto.PageRequestDTO;
import org.may.guestbook.dto.PageResultDTO;
import org.may.guestbook.entity.Guestbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceTest {
	
	@Autowired
	GuestbookService service;

	@Disabled
	@Test
	public void testRegister() {
		//넘기는 dto 신규 정보
		GuestbookDTO guestbookDTO = GuestbookDTO.builder()
														.title(">>> Test Register Title")
														.content(">>> Test Register Content")
														.writer(">>> Test Register Writer")
														.build();
		// Service를 통해 DB에 저장
		System.out.println(service.register(guestbookDTO));
	}
	
//	@Disabled
	@Test
	public void testList() {
		//요청 page DTO 생성
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
		PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);
		
		for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()) {
			System.out.println(guestbookDTO);
		}
		
	
	}

}

package org.may.guestbook.service;

import org.may.guestbook.dto.GuestbookDTO;
import org.may.guestbook.entity.Guestbook;

public interface GuestbookService {
	//DTO를 받아서 Entity로 생성
	//dto 등록
	Long register(GuestbookDTO dto);
	//entity 생성
	default Guestbook dtoToEntity(GuestbookDTO dto) {
		Guestbook entity = Guestbook.builder()
									.gno(dto.getGno())
									.title(dto.getTitle())
									.content(dto.getContent())
									.writer(dto.getWriter())
									.build();
		
		return entity;
	
		};
}

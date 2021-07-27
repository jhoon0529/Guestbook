package org.may.guestbook.service;


import org.may.guestbook.dto.GuestbookDTO;
import org.may.guestbook.dto.PageRequestDTO;
import org.may.guestbook.dto.PageResultDTO;
import org.may.guestbook.entity.Guestbook;

public interface GuestbookService {
	
	//등록하기. dto 를 받아 entity 로 생성(변환) 후, 식별 가능한 (PK)Long 타입으로 받음.
	Long register(GuestbookDTO dto);
	//불러오기.
	PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);
	
	
	
	
	
	
	
	
	//dto->entity (DB에 저장할 때는 entity로 변환 저장)
	default Guestbook dtoToEntity(GuestbookDTO dto) {
		//Entity class Type
		Guestbook entity = Guestbook.builder()
									.title(dto.getTitle())
									.content(dto.getContent())
									.writer(dto.getWriter())
									.build();	//나머지는 Entity가 자동생성
		return entity;
	
		};
		
	//entity->dto	(DB에서 불러올 때는 DTO로 변환)
	default GuestbookDTO entityToDto(Guestbook entity) {
		//DTO class Type
		GuestbookDTO dto = GuestbookDTO.builder()
									.gno(entity.getGno())
									.title(entity.getTitle())
									.content(entity.getContent())
									.writer(entity.getWriter())
									.regDate(entity.getRegDate())
									.modDate(entity.getModDate())
									.build();
		return dto;
	}
}

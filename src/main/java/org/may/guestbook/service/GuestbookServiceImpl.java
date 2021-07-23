package org.may.guestbook.service;

import org.may.guestbook.dto.GuestbookDTO;
import org.may.guestbook.entity.Guestbook;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService {
	@Override
	public Long register(GuestbookDTO dto) {
		
		log.info(">>>>> DTO Register Run");
		log.info(dto);
		
		Guestbook entity = dtoToEntity(dto);	//Service 에서 생성된 Entity 생성 메소드
		
		log.info(entity);			//entity를 repository.save(entity)로 DB에 등록
		
		return null;
	}
}

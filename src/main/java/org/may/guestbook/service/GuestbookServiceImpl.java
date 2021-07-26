package org.may.guestbook.service;

import java.util.function.Function;

import org.may.guestbook.dto.GuestbookDTO;
import org.may.guestbook.dto.PageRequestDTO;
import org.may.guestbook.dto.PageResultDTO;
import org.may.guestbook.entity.Guestbook;
import org.may.guestbook.repository.GuestbookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService {
	
	GuestbookRepository repository;
	
	@Override
	public Long register(GuestbookDTO dto) {
		
		log.info(">>>>> DTO Register Run");
		log.info(dto);
		
		Guestbook entity = dtoToEntity(dto);	//Service 에서 생성된 Entity 생성 메소드
		
		log.info(entity);			//entity를 repository.save(entity)로 DB에 등록
		
		return null;
	}

	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
		Page<Guestbook> result = repository.findAll(pageable);
		Function<Guestbook, GuestbookDTO> fn = (entity->entityToDto(entity));
		
		
		return new PageResultDTO<>(result, fn);
	}
}

package org.may.guestbook.service;

import java.util.function.Function;

import org.may.guestbook.dto.GuestbookDTO;
import org.may.guestbook.dto.PageRequestDTO;
import org.may.guestbook.dto.PageResultDTO;
import org.may.guestbook.entity.Guestbook;
import org.may.guestbook.repository.GuestbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor //NoArgsConstructor, AllArgsConstructor 포함
public class GuestbookServiceImpl implements GuestbookService {
	
	private final GuestbookRepository repository;
	
	//글 등록 메소드
	@Override
	public Long register(GuestbookDTO dto) {
		
		log.info(">>>>> DTO Register Run");
		log.info(dto);
		Guestbook entity = dtoToEntity(dto);	//Service 에서 생성된 Entity 생성 메소드
		
		//dto값으로 만든 entity를 로 DB에 저장
		repository.save(entity);	
		
		return entity.getGno();		//PK값인 Long 타입 gno 출력
	}

	//getList() 메소드 : 
	//출력 타입을 생성한 클래스 PageResultDTO<>로  출력
	//Page<> 사용을 위해 Pageable(page,size,+Sort) 생성.
	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
		Page<Guestbook> result = repository.findAll(pageable);	//pageable로 정렬
		Function<Guestbook, GuestbookDTO> fn = (entity->entityToDto(entity));
		
//		java.util.function : Function<T, R> 
//		T = input type, R = result Type (ex: T=entity Type, R=dto Type)
//		Default Method : R apply(T t)
//		(ex: entity 타입 값을 받아 -> dto 타입으로 리턴하는 기능.)
//		
//		Function<Guestbook, GuestbookDTO> fn =
//				new Function<>() {
//							@Override
//							public GuestbookDTO apply(Guestbook entity) {
//								return entityToDto(entity);
//							}};
						 
		return new PageResultDTO<>(result, fn);
	}
}

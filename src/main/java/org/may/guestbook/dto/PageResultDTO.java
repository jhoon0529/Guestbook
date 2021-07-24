package org.may.guestbook.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import lombok.Data;

@Data
public class PageResultDTO<DTO, EN> {

	private List<DTO> dtoList;

	//page<Entity>로 나온 결과를 DTO로 변환하여 dtoList 에 저장
	//EN = entity
	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
		
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		
	}
	
	
}

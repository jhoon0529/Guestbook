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

	//Constructor
	//Page<EN> result : Entity Type
	//Function<EN,DTO> fn : Entity-> DTO로 전환 기능
	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
		
		//result 와 fn을 Parma으로 받아 이용해 새로운 Collectors List<DTO>에 넣기
		//result와 fn을 mapping하여 collect list로 dtoList에 저장
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		
	}

	
}

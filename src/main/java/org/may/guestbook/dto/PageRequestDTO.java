package org.may.guestbook.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class PageRequestDTO {
	//PageRequestDTO에서 getPageable()를 호출하여 sort값만 지정하여 사용

	//field
	private int page;
	private int size;
	
	//constructor
	public PageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}
	
	//method : Pageable 타입
	public Pageable getPageable(Sort sort) {		//정렬조건
		
		return PageRequest.of(page-1, size, sort);
		
	}
	
}

package org.may.guestbook.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

	//field
	private int page;
	private int size;
	
	//constructor
	public PageRequestDTO(int page, int size) {
		this.page = 1;
		this.size = 10;
	}
	
	public Pageable getPageable(Sort sort) {		//정렬조건
		
		return PageRequest.of(page-1, size, sort);
		
	}
	
}

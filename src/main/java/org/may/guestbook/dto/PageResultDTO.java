package org.may.guestbook.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageResultDTO<DTO, EN> {

	//entity->dto 전환 된 List
	private List<DTO> dtoList;
	//현재 페이지
	private int page;
	//목록 사이즈
	private int size;
	//이전 다음 페이지
	private boolean prev,next;
	//전체 페이지
	private int totalPage;
	//시작,끝 페이지
	private int start,end;
	//페이지 번호 목록
	private List<Integer> pageList;
	

	//page<Entity>로 나온 결과를 DTO로 변환하여 dtoList 에 저장
	//EN = entity

	//Constructor
	//Page<EN> result : Entity Type
	//Function<EN,DTO> fn : Entity-> DTO로 전환 기능
	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
		
		//result 와 fn을 Parma으로 받아 이용해 새로운 Collectors List<DTO>에 넣기
		//result와 fn을 mapping하여 collect list로 dtoList에 저장
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		
		totalPage = result.getTotalPages();	//Page<>.getTotalPages() : 전체 수량을 page size로 나눔
		makePageList(result.getPageable());
		
	}
	
	private void makePageList(Pageable pageable) {
		
		this.page = pageable.getPageNumber() +1; //페이지는 0번부터 시작하기 때문
		this.size = pageable.getPageSize();
		
		//temp end page : 현재 보여지는 끝 페이지
		int tempEnd = (int)Math.ceil(page/10.0)*10; 
		//ex) 13page 일경우 13/10.0 = (int)1.3 = 2 * 10 = 20 page 까지 보임. 즉, 11~20page까지는 20page까지 보임
		start = tempEnd -9;	//보여지는 끝 page가 20일 경우 -9 = 11page
		end = totalPage > tempEnd ? tempEnd :totalPage; //현재페이지가 전체랑 같으면 전체페이지
		prev = start > 1 ;
		next = end < 1;
		//새로운 페이지 목록번호를 만들어 저장
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
	
	

	
}

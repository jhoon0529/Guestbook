package org.may.guestbook.repository;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.may.guestbook.entity.Guestbook;
import org.may.guestbook.entity.QGuestbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootTest
public class GuestbookRepositoryQueryDslTests {

	@Autowired
	GuestbookRepository guestbookRepository;
	
	@Disabled
	@Test	//단일 항목 검색 (.contains(키워드))
	void testFirstQuery1() {
		//조회 리스트
		Pageable pageable = PageRequest.of(0, 50, Sort.by("gno").descending());
		
		//Predicate
		QGuestbook qGuestbook =QGuestbook.guestbook;

		//검색 조건
		String keyword = "1";		//검색 문자
		//BooleanBuilder : 검색조건 생성
		BooleanBuilder builder = new BooleanBuilder();	
		
		//BooleanExpression : 속성 Like '%1%'
		BooleanExpression expression = qGuestbook.title.contains(keyword);
		//builder 에 (포함,미포함) expression 추가
		builder.and(expression);		
		//builder.andNot(expression);		
		
		//Querydsl 사용 : findAll 사용-> JpaRepository(X), QuerydslPredicateExecutor(O)
		Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);
		result.forEach(guestbook->{
				System.out.println(guestbook);
			});
	}
	
	@Disabled
	@Test	// 다중 컬럼 조건 검색
	void testManyQuery2() {
		Pageable pageable = PageRequest.of(0, 50, Sort.by("gno").ascending());
		QGuestbook qGuestbook = QGuestbook.guestbook;
		
		String keyword = "5";
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		BooleanExpression exTitle= qGuestbook.title.contains(keyword);
		BooleanExpression exContent= qGuestbook.content.contains(keyword);
		BooleanExpression exAll= exTitle.or(exContent);		//Expression 조건들의 합
		
		booleanBuilder.and(exAll);
		booleanBuilder.and(qGuestbook.gno.gt(20L));	//gt() : gno > 0;
		
		Page<Guestbook> list = guestbookRepository.findAll(booleanBuilder, pageable);
		list.forEach(result->{
			System.out.println(result);
		});
	}
	
	
	
}

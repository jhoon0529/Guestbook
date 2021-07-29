package org.may.guestbook.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.may.guestbook.entity.Guestbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookRepositoryTests {

	@Autowired
	GuestbookRepository guestbookRepository;
	
	//insert : save()
	//update: findById().get() + save()  //findById() = Return 타입 Optional<T>
	//delete : findById().get() + delete()
	//selete : findById().get()  or  findAll()
	
	
	@Disabled
	@Test	//삽입
	void insertDummies() {
		IntStream.rangeClosed(0, 50).forEach(i->{
		
		Guestbook guestbook = Guestbook.builder()
											.title("Title..."+i)
											.content("Content..."+i)
											.writer("Writer..."+(i % 10))
											.build();

		//DB 저장 : save() 는 insert into
		System.out.println(guestbookRepository.save(guestbook));
		});
	}
	@Disabled
	@Test	//단일 삽입
	void insertOne() {
//		Long i = 49L;	//gno를 지정하여도 추가 자동 발번 됨.
		Guestbook newGuestbook = Guestbook.builder()
//												 .gno(i)
												 .title("New Title...")
												 .content("New Content...")
												 .writer("New Writer...")
												 .build();
		//DB 저장 : save() 는 insert into										
		System.out.println(guestbookRepository.save(newGuestbook));
	}
	
	@Disabled
	@Test	//수정
	void updateTest() {
		Optional<Guestbook> findId = guestbookRepository.findById(10L);
		
		if(findId.isPresent()) { //value != null
			
			Guestbook guestbook = findId.get(); //value == null, false=>return value
			guestbook.changeTitle("Changed Title..."+guestbook.getGno());
			guestbook.changeContent("Changed Content..."+guestbook.getGno());
			
			//DB 저장 : save() 는 update TABLE set	속성=? where findId=?
			System.out.println("Optional<T>: "+findId); // Collection 타입 []
			System.out.println(guestbookRepository.save(guestbook)); //객체타입()
		}
	}
	
	@Disabled
	@Test	//삭제(객체 조회 삭제방식 .delete())
	void deleteTest() {
		Guestbook guestbook = guestbookRepository.findById(50L).get();
		
		//DB 저장 :delete() 는 delete from TABLE where findByID=?
		guestbookRepository.delete(guestbook);
		System.out.println(guestbook);
	}
	
	@Disabled
	@Test	//삭제(ID 조회 방식 .deleteByID())
	void deleteTestById() {
		guestbookRepository.deleteById(49L);;
		
		//DB 저장 :deleteById() 는 delete from TABLE where deleteByID=? 실행
		System.out.println(guestbookRepository.findById(49L));
	}
	
//	@Disabled
	@Test	//전체리스트 조회
	void seleteTest() {
		List<Guestbook> guestbookList = guestbookRepository.findAll();
		
		System.out.println(guestbookList); //[]형식 1줄로 출력
		for (Guestbook getList : guestbookList) {	//1줄씩 출력
			System.out.println(getList);
		}
	}
	
	@Disabled
	@Test	//단일 조회
	void getTest() {
		Guestbook getOne = guestbookRepository.findById(10L).get();			//리턴 : Entity()
		
		System.out.println(getOne);
//		System.out.println(guestbookRepository.findById(1L));					//리턴 : Optional[]
//		System.out.println(guestbookRepository.findById(1L).toString());		//리턴 : Optional[]
//		System.out.println(guestbookRepository.findById(1L).getClass());	//리턴 : class java.util.Optional
	}
	
	
}

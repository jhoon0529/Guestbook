package org.may.guestbook.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass	// SuperClass 로만 사용, 실제 Entity 사용되지 않음(추상클래스)
@EntityListeners(value = { AuditingEntityListener.class })	//상속받는 엔티티들에 생성, 수정 시간을 기록 
@Getter
abstract class BaseEntity {

	@CreatedDate																//생성시간 표기
	@Column(name = "regdate", updatable = false)				//updatable 수정 가능 여부
	private LocalDateTime regDate;
	
	@LastModifiedDate															//최종수정시간 표기
	@Column(name = "moddate")
	private LocalDateTime modDate;
	
	
	
}

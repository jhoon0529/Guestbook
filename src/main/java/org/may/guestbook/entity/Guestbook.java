package org.may.guestbook.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Guestbook extends BaseEntity {
	//상속 받은 속성 SQL
	//moddate datetime(6),
    //regdate datetime(6),
	//engine=InnoDB  : MariaDB 엔진


	@Id																				//primary key (gno)
	@GeneratedValue(strategy = GenerationType.IDENTITY) //gno bigint not null auto_increment
	private Long gno;
	
	@Column(length = 100, nullable = false)							//title varchar(100) not null
	private String title;
	
	@Column(length = 1500, nullable = false)
	private String content;
	
	@Column(length = 50, nullable = false)
	private String writer;
}

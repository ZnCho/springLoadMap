package hello.hellospring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity //jpa가 관리하는 entity
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) //db가 알아서 seq 만들어주는 것
	private Long id; //시스템이 저장하는 아이디. 임의의 값.
//	@Column(name = "userName") db 칼럼 명과 다를 경우 이런 식으로 연결시켜줌
	private String name; //이름

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}

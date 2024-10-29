package hello.helloSpring.domain;

public class Member {
	private Long id; //시스템이 저장하는 아이디. 임의의 값.
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

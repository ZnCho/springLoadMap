package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();

	@AfterEach //아래 메소드들의 실행이 끝날때마다 호출됨. 콜백함수. 테스트는 순서와 관계없이 의존관계 없도록 설계 해야함
	public void afterEach(){
		repository.clearStore();
	}

	@Test
	public void save(){ //테스트 할 메소드와 같은 이름으로 명명
		Member member = new Member();
		member.setName("spring");

		repository.save(member);

		Member result = repository.findById(member.getId()).get();
//		System.out.println("result = " + (result == member));
//		Assertions.assertEquals(member, null); //기대값이 다르면 오류 뜸, 같으면 초록불 들어옴
		assertThat(member).isEqualTo(result);
	}

	@Test
	public void findById(){
		Member member1 = new Member();
		member1.setName("spring");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);

		Member result = repository.findByName("spring").get();
		assertThat(result).isEqualTo(member1);
	}

	@Test
	public void findAll(){
		Member member1 = new Member();
		member1.setName("spring");
		repository.save(member1);

		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);

		List<Member> result = repository.findAll();

		assertThat(result.size()).isEqualTo(2);
	}

}

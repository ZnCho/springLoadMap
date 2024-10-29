package hello.helloSpring.service;

import hello.helloSpring.domain.Member;
import hello.helloSpring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

	MemberService memberService;
	MemoryMemberRepository memberRepository;

	@BeforeEach //실행하기 전에
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository); //같은 멤버레포지토리 사용하게 함. 멤버 서비스 입장에서 DI(디펜던시 인젝션 = 의존성 주입)
	}

	@AfterEach //실행한 후에
	public void afterEach() {
		memberRepository.clearStore();
	}

	// 테스트는 함수명을 한글로 적어도 됨
	// 테스트는 예외 처리가 중요함
	@Test
	void 회원가입() {
		//given - when - then 문법
		//given : 어떤 상황이 주어졌을 때
		Member member = new Member();
		member.setName("spring");

		//when : 이걸 실행했을 때
		Long saveId = memberService.join(member);

		//then : 이런 결과가 나와야 함
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
	}

	@Test
	public void 중복_회원_예외(){
		//given
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");

		//when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
		try {
			memberService.join(member2);
			fail("예외가 발생해야 합니다.");
		} catch (IllegalStateException e) {
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		}
*/

		//then
	}

	@Test
	void findMembers() {

	}

	@Test
	void findOne() {
	}
}
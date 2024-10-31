package hello.helloSpring.service;

import hello.helloSpring.domain.Member;
import hello.helloSpring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
	private final MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	/**서비스 클래스는 비즈니스에 가까운 용어를 써야됨
	 * 서비스는 비지니스에 의존적으로 설계.
	 * 레포지토리는 더 기계적으로 개발스럽게 용어 선택.
	 */

	/**
	 * 회원 가입
	 */
	public Long join(Member member) {
		validateDupliateMember(member); //중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDupliateMember(Member member) {
// 		Optional로 객체를 한 번 감싸서 사용하면 Optional 안의 다양한 메소드를 사용할 수 있음
//			result.get() 이렇게 해서 Member 객체의 값을 꺼낼 수 있는데 권장하진 않음
//			result.orElseGet() 값이 있으면 꺼내고 값이 없으면 이 안에 있는 메소드를 실행함
		memberRepository.findByName(member.getName())
						.ifPresent(m->{//ifPresent 값이 있으면 (if null 대신)
							throw new IllegalStateException("이미 존재하는 회원입니다.");
						});
	}

	/**
	 * 전체 회원 조회
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}

	public Optional<Member> findOne(Long memeberId) {
		return memberRepository.findById(memeberId);
	}
}

package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
	Member save(Member member);
	//Optional = 가져왔을 때 null이 반환되는데 그대로 반환하는 대신 옵셔널로 감싸서 반환함
	Optional<Member> findById(Long id);
	Optional<Member> findByName(String name);
	List<Member> findAll();
}

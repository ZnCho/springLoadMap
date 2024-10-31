package hello.helloSpring.repository;

import hello.helloSpring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

	//JPQL select m form Member m where m.name = ?
	//findByNameAndId(String name, Long Id) 이렇게 선언하면
	//select m form Member m where m.name = ? and m.Id = ? 이렇게 JPQL을 작성
	//and 말고 or 을 쓸 수도 있음 = 메서드 이름만으로도 조회 기능 제공
	@Override
	Optional<Member> findByName(String name);
}

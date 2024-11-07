package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

	//JPA 라이브러리를 받으면 스프링 부트가 자동으로 EntityManger를 생성해줌, DB와 연결도 시켜서 생성해주니 JPA를 쓰려면 em을 주입만 하면 됨
	private final EntityManager em;

	public JpaMemberRepository(EntityManager em) {this.em = em;}

	@Override
	public Member save(Member member) {
		em.persist(member); //persist 영속하다, 영구 저장하다 = insert
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	/**
	 * pk 기반으로 검색하지 않거나 다건 조회일 경우 쿼리를 적어야 함
	 */
	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
							    .setParameter("name", name)
							    .getResultList();

		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		/** select m from Member m > JPA Query Language 라는 쿼리 언어 형식
		 *  객체(엔티티)를 대상으로 쿼리 날림
		 *  객체 자체를 셀렉트 해서 select * 이 아니라 m 이라 함
		 */
		return em.createQuery("select m from Member m", Member.class).getResultList();
	}
}

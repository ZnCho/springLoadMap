package hello.helloSpring;

import hello.helloSpring.repository.JdbcMemberRepository;
import hello.helloSpring.repository.JdbcTemplateMemberRepository;
import hello.helloSpring.repository.JpaMemberRepository;
import hello.helloSpring.repository.MemberRepository;
import hello.helloSpring.repository.MemoryMemberRepository;
import hello.helloSpring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//	DataSource dataSource;
//	@Autowired
//	public SpringConfig(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}

//	private EntityManager em;
//	@Autowired
//	public SpringConfig(EntityManager em) {
//		this.em = em;
//	}

	private final MemberRepository memberRepository;
	@Autowired //생성자가 1개니까 생략해도 됨
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
//		return new MemberService(memberRepository());
		return new MemberService(memberRepository);
	}

//	@Bean
//	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//		return new JdbcMemberRepository(dataSource); //새로 만든 JdbcMemberRepository로 변경 및 dataSource 주입
//		return new JdbcTemplateMemberRepository(dataSource);
//		return new JpaMemberRepository(em);
//	}
}

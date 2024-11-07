package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

	//빈으로 등록하지 않고 @Component 달음
//	@Bean
//	public TimeTraceAop timeTraceAop() {
//		return new TimeTraceAop();
//	}

//	@Bean
//	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//		return new JdbcMemberRepository(dataSource); //새로 만든 JdbcMemberRepository로 변경 및 dataSource 주입
//		return new JdbcTemplateMemberRepository(dataSource);
//		return new JpaMemberRepository(em);
//	}
}

package hello.helloSpring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //AOP를 쓰기 위해 필요한 어노테이션
@Component
public class TimeTraceAop {

	@Around("execution(* hello.helloSpring..*(..))") //어디에 적용할지 타겟팅
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("START : " + joinPoint.toString());
		try {
			//다음 메소드로 진행
			return joinPoint.proceed();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
		}
	}
}

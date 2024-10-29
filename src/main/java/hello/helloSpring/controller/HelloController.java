package hello.helloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!!"); //hello.html에서 이 attributeName을 찾아서 넘겨줌.
		return "hello"; //resources/templates/hello.html 똑같은 이름. 이 화면을 실행시키고, 모델의 데이터를 넣어준다.
	}

	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam(value="name", required = false) String name, Model model) {
		//@RequestParam 옵션 중 required의 기본 값은 true = 필수
		model.addAttribute("name", name); //model.addAttribute(key, value)
		return "hello-template";
	}

	@GetMapping("hello-string")
	@ResponseBody // http의 바디부에 이 데이터를 직접 넣어주겠다는 뜻
	public String helloString(@RequestParam(value="name", required = false) String name) {
		return "hello " + name; //name이 spring이면 "hello spring" > 문자 그대로 나옴
	}

	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam(value="name", required = false) String name) {
		//JSON 방식 : key - value 형식
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}

	static class Hello {
		//private 변수는 getter/setter 생성해서 사용
		//프로퍼티 접근 방식이라고도 부름
		private String name;

		// 컨트롤(커맨드)+N로 생성
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
}

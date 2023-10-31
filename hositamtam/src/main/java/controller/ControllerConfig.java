package controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import model.MemberDAO;

@Configuration
public class ControllerConfig {

	@Bean
	public MemberDAO memberDAO() {	
		
		return new MemberDAO();
	}
	
	@Bean
	public MemberController MemberController() {
		return new MemberController(this.memberDAO());
	}
	
	@Bean
	public MarketAndStoreController marketAndStoreController() {
		return new MarketAndStoreController();
	}
	@Bean
	public TestController testController() {
		return new TestController();
	}
	@Bean
	public PostController postController() {
		return new PostController();
	}
}

package controller;


import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TestController {
	

	public TestController() {
	}

	@GetMapping("/views/test")
	private String test() {
		
		return "test";
	}
}

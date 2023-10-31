package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.PostDAO;

@Controller
public class PostController {
	
	private PostDAO postDAO = new PostDAO();
	
	public PostController() {
	}
	
	public PostController(PostDAO postDAO) {
		this.postDAO = postDAO;
	}
	// 시장 전체글 조회
	@GetMapping("/views/postMain")
	public String MarketList(@RequestParam int mno, Model model) {
		model.addAttribute("postList", postDAO.getAllPost(mno));
		return "post";
	}
	@GetMapping("/views/postCatrgoryList")
	public String MarketList(@RequestParam String pCategory, Model model) {
		model.addAttribute("postList", postDAO.getAllPost(pCategory));
		return "post";
	}
}

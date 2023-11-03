package controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.PostDO;
import model.PostDAO;
@Controller
public class PostController {
	
	private PostDAO postDAO = new PostDAO();
	
	public PostController() {
	}
	
	public PostController(PostDAO postDAO) {
		this.postDAO = postDAO;
	}
	
	@GetMapping("/views/postForMarketList")
	public String postForMarketList(Model model) {
		model.addAttribute("marketList", postDAO.getAllMarket());
		return "postForMarketList";
	}
<<<<<<< Updated upstream

	// 전체글 조회
	@GetMapping("/views/postMain")
	public String postMain(@RequestParam int mno, Model model) {
		model.addAttribute("postList", postDAO.getAllPost(mno));
		model.addAttribute("market", postDAO.getSelectedMarket(mno));
		return "postList";
	}
	// 인기글
	@GetMapping("/views/postHot")
	public String postHot(@RequestParam int mno, Model model) {
		model.addAttribute("postList", postDAO.getPostHot(mno));
		model.addAttribute("market", postDAO.getSelectedMarket(mno));
		return "postList";
	}
	// 카테고리별
	@GetMapping("/views/postCategory")
	public String postCategory(@RequestParam int mno,@RequestParam String pCategory, Model model) {
		model.addAttribute("postList", postDAO.getPostCategory(mno, pCategory));
		model.addAttribute("market", postDAO.getSelectedMarket(mno));
		return "postList";
	}
	// 글 등록 이동
	@GetMapping("/views/toPostUpdate")
	public String toPostUpdate(@RequestParam int mno, Model model, HttpSession session) {
		model.addAttribute("market", postDAO.getSelectedMarket(mno));
		return "postUpdate";
	}
	// 글 등록 기능
	@PostMapping("/views/postUpdate")
	public String PostUpdate(@ModelAttribute PostDO command, Model model) {
		System.out.println("맵핑 성공");
		postDAO.insertPost(command);
		model.addAttribute("postList", postDAO.getAllPost(command.getMno()));
		model.addAttribute("market", postDAO.getSelectedMarket(command.getMno()));
		System.out.println("뷰 처리");
		return "postList";
=======
	@GetMapping("/views/postCatrgoryList")
	public String MarketList(@RequestParam String pCategory, Model model) {
//		model.addAttribute("postList", postDAO.getAllPost(pCategory));
		return "post";
>>>>>>> Stashed changes
	}
}

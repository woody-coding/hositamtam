package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.PostDAO;
import oracle.net.aso.p;
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

	// 전체글 조회
	@GetMapping("/views/postMain")
	public String postMain(@RequestParam int mno, Model model) {
		model.addAttribute("postList", postDAO.getAllPost(mno));
		model.addAttribute("mno", mno);
		return "postList";
	}
	// 인기글
	@GetMapping("/views/postHot")
	public String postHot(@RequestParam int mno, Model model) {
		model.addAttribute("postList", postDAO.getPostHot(mno));
		model.addAttribute("mno", mno);
		return "postList";
	}
	// 카테고리별
	@GetMapping("/views/postCategory")
	public String postCategory(@RequestParam int mno,@RequestParam String pCategory, Model model) {
		model.addAttribute("postList", postDAO.getPostCategory(mno, pCategory));
		model.addAttribute("mno", mno);
		return "postList";
	}
	// 글 등록 및 수정로 이동
	@GetMapping("/views/toPostUpdate")
	public String toPostUpdate(@RequestParam int mno, Model model) {
		System.out.println("맵핑성공" + mno);
		model.addAttribute("mname", postDAO.getMarketName(mno));
		System.out.println("뷰 처리" + mno);
		return "postUpdate";
	}
	// 글 등록 및 수정 기능
	@GetMapping("/views/PostUpdate")
	public String PostUpdate(@RequestParam int mno, Model model) {
		model.addAttribute("mName", postDAO.getMarketName(mno));
		
		return "postList";
	}
}

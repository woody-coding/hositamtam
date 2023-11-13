package controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

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
	public String postCategory(@RequestParam int mno, @RequestParam String pCategory, Model model) {
		model.addAttribute("postList", postDAO.getPostCategory(mno, pCategory));
		model.addAttribute("market", postDAO.getSelectedMarket(mno));
		return "postList";
	}

	// 글 등록 이동
	@GetMapping("/views/toPostInsert")
	public String toPostInsert(@RequestParam int mno, Model model, HttpSession session) {
		model.addAttribute("market", postDAO.getSelectedMarket(mno));
		return "postInsert";
	}

	// 글 수정 이동
	@GetMapping("/views/toPostUpdate")
	public String toPostUpdate(@RequestParam int mno, @RequestParam int pno, Model model, HttpSession session) {
		model.addAttribute("market", postDAO.getSelectedMarket(mno));
		model.addAttribute("post", postDAO.getAllPostInfo(pno));
		return "postUpdate";
	}

	// 글 삭제
	@GetMapping("/views/deletePost")
	public String deletePost(@RequestParam int pno, @RequestParam int mno, Model model) {
		model.addAttribute("post", postDAO.deletePost(pno));
		model.addAttribute("postList", postDAO.getAllPost(mno));
		model.addAttribute("market", postDAO.getSelectedMarket(mno));
		return "postList";
	}

	// 글 등록 기능
	@PostMapping("/views/postInsert")
	public String insertPost(@ModelAttribute PostDO command, Model model, HttpServletRequest request) {
		
		try	{ 
		System.out.print("컨트롤러 진입");
		String directory = "C:\\projects\\final\\hositamtam\\hositamtam\\src\\main\\webapp\\upload";
		int sizeLimit = 1024 * 1024 * 5;
		MultipartRequest multi = new MultipartRequest(request, directory, sizeLimit, "UTF-8",
				new DefaultFileRenamePolicy());

		File uploadDir = new File(directory);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		String savedName = "";
		@SuppressWarnings("unchecked")
		Enumeration<String> fileNames = multi.getFileNames();
		if (fileNames.hasMoreElements()) {
			String paramName = fileNames.nextElement();
			savedName = multi.getFilesystemName(paramName);
		}
		String postPhoto = "/finalProject/upload/" + savedName;
		System.out.print("파일 정보 저장");

		int mno = Integer.parseInt(multi.getParameter("mno"));
		String id = multi.getParameter("id");
		String ptitle = multi.getParameter("ptitle");
		String pcontent = multi.getParameter("pcontent");
		String pcategory = multi.getParameter("pcategory");

		PostDO post = new PostDO();
		post.setMno(mno);
		post.setId(id);
		post.setPtitle(ptitle);
		post.setPcontent(pcontent);
		post.setPphoto(postPhoto);
		post.setPcategory(pcategory);
		postDAO.insertPost(post);

		model.addAttribute("postList", postDAO.getAllPost(mno));
		model.addAttribute("market", postDAO.getSelectedMarket(mno));
		System.out.print("뷰 처리");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "postList";
	}

	// 글 수정 기능
	@PostMapping("/views/postUpdate")
	public String updatePost(@ModelAttribute PostDO command, Model model, HttpServletRequest request)
			throws IOException {
		String directory = "C:\\projects\\final\\hositamtam\\hositamtam\\src\\main\\webapp\\upload";
		int sizeLimit = 1024 * 1024 * 5;
		MultipartRequest multi = new MultipartRequest(request, directory, sizeLimit, "UTF-8",
				new DefaultFileRenamePolicy());

		File uploadDir = new File(directory);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		String savedName = "";
		@SuppressWarnings("unchecked")
		Enumeration<String> fileNames = multi.getFileNames();
		if (fileNames.hasMoreElements()) {
			String paramName = fileNames.nextElement();
			savedName = multi.getFilesystemName(paramName);
		}
		// 파일 정보를 photo 변수에 저장
		String postImg = "/finalProject/upload/" + savedName; // 웹 경로로 수정

		int pno = Integer.parseInt(multi.getParameter("pno"));
		int mno = Integer.parseInt(multi.getParameter("mno"));
		String id = multi.getParameter("id");
		String ptitle = multi.getParameter("ptitle");
		String pcontent = multi.getParameter("pcontent");
		String pcategory = multi.getParameter("pcategory");
		// 게시물 생성
		PostDO post = new PostDO();
		post.setPno(pno);
		post.setMno(mno);
		post.setId(id);
		post.setPtitle(ptitle);
		post.setPcontent(pcontent);
		post.setPphoto(postImg);
		post.setPcategory(pcategory);
		postDAO.updatePost(post);

		model.addAttribute("postList", postDAO.getAllPost(mno));
		model.addAttribute("market", postDAO.getSelectedMarket(mno));
		return "postList";
	}

	// 게시글 상세 페이지 이동
	@GetMapping("/views/toPostDetail")
	public String toPostDetail(@RequestParam int pno, Model model) {
		model.addAttribute("pno", pno);
		model.addAttribute("post", postDAO.getAllPostInfo(pno));
		model.addAttribute("market", postDAO.getMarketNameByPno(pno));
		model.addAttribute("commentList", postDAO.getComment(pno));
		return "postDetail";
	}

	// 댓글 등록 시
	@PostMapping("/views/InsertComment")
	public String InsertComment(@ModelAttribute PostDO command, Model model) {
		postDAO.InsertComment(command);
		return "redirect:/views/toPostDetail?pno=" + command.getPno();
	}

	// 댓글 삭제 시
	@GetMapping("/views/deleteComment")
	public String deleteComment(@RequestParam int pno, @RequestParam int cno, Model model) {
		postDAO.deleteComment(cno);
		return "redirect:/views/toPostDetail?pno=" + pno;
	}

	@GetMapping("/views/postCatrgoryList")
	public String MarketList(@RequestParam String pCategory, Model model) {
//		model.addAttribute("postList", postDAO.getAllPost(pCategory));
		return "post";
	}
}

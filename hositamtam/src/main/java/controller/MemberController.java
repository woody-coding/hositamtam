package controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import model.TestModel;

import org.apache.catalina.util.URLEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.MemberDAO;
import model.MemberDO;
/*
*메인
최초시작 => memberController.jsp 	-> main.jsp

1) 메인			|GET 	| /toMain 			-> main.jsp
2) 검색			|GET 	| /toMarket			-> 검색어 파라미터 값을 가지고 marketController.java 	-> marketList.jsp(검색어)
3) 로그인			|GET 	| /toLogin			-> login.jsp
4) 회원가입 		|GET	| /toJoin			-> join.jsp
5) 시장리스트		|GET 	| /toMarket			=> 카테고리 파라미터 값을 가지고 marketController.java 	-> marketList.jsp(카테고리)
6) 시끌시끌		|GET 	| /toPost 			-> post.html 

*로그인
7)로그인			|POST	| /loginMember		-> 로그인 처리 후 main.jsp

*회원가입
8)회원가입			|POST	| /joinMember		-> 회원가입 처리 후 login.jsp

*마이페이지
9)비밀번호변경		|POST	| /updatePasswd		-> 사용자 입력값을 통해 수정 후 login.jsp
10)닉네임변경		|POST	| /updateNickname	-> 사용자 입력값을 통해 수정 후 login.jsp
11)회원정보안내		|POST	| /getAllMemberInfo	-> 회원정보를 읽은 후 myPage.jsp
12)회원탈되		|POST	| /deleteMember			-> 회원탈퇴 처리 후, main.jsp


*/

@Controller
public class MemberController {

	private MemberDAO memberDAO;
	
	public MemberController() {
	}
	
	public MemberController(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	// 헤더
	@GetMapping("/views/main") // http://localhost:8080/finalProject/views/main
	public String toMain(HttpSession session, Model model) {
		// 세션에서 userId 값을 불러옴
	    String userId = (String) session.getAttribute("userId");

	    // 사용자 정보를 세션에서 가져옴
	    MemberDO user = (MemberDO) session.getAttribute("user");

	    if (userId != null && user != null) {
	        // 로그인 상태라면 사용자 정보를 모델에 추가하여 뷰에서 사용 가능
	        model.addAttribute("userId", userId);
	        model.addAttribute("user", user); // 사용자 정보를 모델에 추가

	        return "main"; // main.jsp와 같은 뷰로 이동
	    } else {
	        // 로그인하지 않은 경우 처리
	        return "redirect:/views/login"; // 로그인 페이지로 리다이렉트
	    }
	}
	
	@GetMapping("/views/login")
	public String toLogin() {
		return "login";
	}
	@GetMapping("/views/join")
	public String toJoin() {
		return "join";
	}
	@GetMapping("/views/post")
	public String toPost() {
		int mno = 100;
		return "redirect:/views/postMain?mno=" + mno;
	}

	// marketAndStoreController로 이동
	@GetMapping("/views/market")
	public String toMarket() {
		return "redirect:/views/marketList";
	}
	@PostMapping("/views/marketCategory")
	public String toMarketByCategory(@RequestParam String cateno) {
		return "redirect:/views/marketByCategory?cateno=" + cateno;
	}
	@GetMapping("/views/marketSearch")
	public String toMarketBySearch(@RequestParam String keyword) throws UnsupportedEncodingException {
		keyword = java.net.URLEncoder.encode(keyword, "UTF-8");
		return "redirect:/views/marketBySearch?keyword=" + keyword;
	}
	

	// 회원가입 화면
	@PostMapping("/views/joinMember")
	public String insert(@ModelAttribute MemberDO command, Model model) throws Exception {
	    

//	    String idDuplicateMessage = memberDAO.isIdDuplicate(command);
//	    String nicknameDuplicateMessage = memberDAO.isNicknameDuplicate(command);
//
//	    if (!idDuplicateMessage.isEmpty()) {
//	        // 아이디 중복 메시지가 비어있지 않으면 중복
//	        model.addAttribute("msg", idDuplicateMessage);
//	        viewName = "join";
//	        
//	    } else if (!nicknameDuplicateMessage.isEmpty()) {
//	        // 닉네임 중복 메시지가 비어있지 않으면 중복
//	        model.addAttribute("msg", nicknameDuplicateMessage);
//	        viewName = "join";
//	        
//	    } else {
//	        // 중복이 없을 경우 회원 등록
//	        try {
//	            memberDAO.joinMember(command);
//	            viewName = "redirect:/views/joinMember";
//	        } catch (Exception e) {
//	            model.addAttribute("msg", e.getMessage());
//	            model.addAttribute("join", memberDAO.getMember(command.getId()));
//	            viewName = "join";
//	        }
//	    }
	    
		String viewName = "";
		
	    try {
            memberDAO.joinMember(command);
            viewName = "redirect:/views/join";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("join", memberDAO.getMember(command.getId()));
            viewName = "join";
        }

	    return viewName;
	}
	
	
	//로그인 화면
	@PostMapping("/views/loginMember")
	public String login(@RequestParam String id, @RequestParam String passwd, HttpSession session, Model model) throws Exception {
	    
		try {
//			System.out.println(id + passwd);

		    // 로그인 처리 성공 유무에 따른 화면 출력
		    if (memberDAO.loginMember(id, passwd)) {
		        session.setAttribute("userId", id);
//		        System.out.println(session.getAttribute("userId"));
		        return "redirect:/views/main"; // 로그인 성공 시 메인 페이지로 이동
		    } else {
		        return "redirect:/views/login"; // 로그인 실패 시 다시 로그인 페이지로
		    }
		}catch(Exception e) {
			return "redirect:/views/login";
		}
		
	}


	// 회원 계정 화면
	@GetMapping("/views/myPage")
	public void toMyPage(@RequestParam("id") String id) {
		
	}
	
	@GetMapping("/views/myPageUpdate")
	public void toMyPageUpdate(@RequestParam("id") String id) {
	}
	
	@PostMapping("/myPage/updatePasswd")
	public String updatePasswd(@ModelAttribute MemberDO command) {
//		memberDAO.updatePasswd(command);
			
		return "redirect:/myPage";
	}
	
	@PostMapping("/views/myPage/updateNickname")
	public String updateNickname(@ModelAttribute MemberDO command) {
//		memberDAO.updateNickname(command);
			
		return "redirect:/myPage";
	}
	
	@PostMapping("/views/myPage/deleteMember")
	public String deleteMember(@RequestParam("id") String id) {
//		memberDAO.deleteMember(id);
		
		return "redirect:/main";
	}
}

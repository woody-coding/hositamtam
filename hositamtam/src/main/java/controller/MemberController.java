package controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import model.TestModel;

import org.apache.catalina.util.URLEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.StoreDAO;
import model.StoreDO;
import model.MemberDAO;
import model.MemberDO;
import model.PostDAO;
import model.PostDO;
import model.ReviewDAO;
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
import model.ReviewDO;

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
		
		// 세션에서 사용자 아이디와 사용자 정보 가져오기
	    String userId = (String) session.getAttribute("userId");
	    MemberDO memberInfo = (MemberDO) session.getAttribute("memberInfo");

	    if(userId != null && memberInfo != null) {
	        // 사용자 정보가 세션에 저장되어 있으면 이 정보를 모델에 추가
	        model.addAttribute("userId", userId);
	        model.addAttribute("memberInfo", memberInfo);
	        
		}else{
		    // 로그인된 사용자 정보가 세션에 없음 (로그아웃 상태)
			
		}
		

		return "main";
	}
	
	@GetMapping("/views/login")
	public String toLogin(Model model) {
		return "login";
	}
	
	
	@GetMapping("/views/loginAgain")
	public String loginAgain(HttpSession session) {
        // 로그아웃 처리
		session.removeAttribute("memberInfo"); // 세션에서 사용자 정보 삭제
	    session.removeAttribute("userId"); // 세션에서 사용자 아이디도 삭제
        session.invalidate(); // 세션 초기화
        return "redirect:/views/login"; // 로그아웃 후 메인 페이지로 리다이렉트
    }
	
	
	@GetMapping("/views/logout")
	public String logout(HttpSession session) {
        // 로그아웃 처리
		session.removeAttribute("memberInfo"); // 세션에서 사용자 정보 삭제
	    session.removeAttribute("userId"); // 세션에서 사용자 아이디도 삭제
        session.invalidate(); // 세션 초기화
        return "redirect:/views/main"; // 로그아웃 후 메인 페이지로 리다이렉트
    }
	
	@GetMapping("/views/join")
	public String toJoin() {
		return "join";
	}
	@GetMapping("/views/post")
	public String toPost() {
		return "redirect:/views/postForMarketList";
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
	
/*
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
            viewName = "redirect:/views/login";
        } catch (Exception e) {
            model.addAttribute("msg", e.getMessage());
            model.addAttribute("join", memberDAO.getMember(command.getId()));
            viewName = "redirect:/views/join";
        }

	    return viewName;
	}
*/	
	
	//로그인 화면
	@PostMapping("/views/loginMember")
	public String login(@RequestParam String id, @RequestParam String passwd, HttpSession session, Model model) throws Exception {
	    
		try {
			System.out.println(id + passwd);

//			System.out.println(memberDAO.loginMember(id, passwd));
		    // 로그인 처리 성공 유무에 따른 화면 출력
		    if (memberDAO.loginMember(id, passwd)) {		

		        session.setAttribute("userId", id);
		        model.addAttribute("userId", session.getAttribute("userId"));

		        
		        MemberDO memberInfo = memberDAO.getMember(id);
		        session.setAttribute("memberInfo", memberInfo);
		        model.addAttribute("memberInfo", memberInfo);
		        System.out.println("memberInfo");
		        

		        return "redirect:/views/main"; // 로그인 성공 시 메인 페이지로 이동
		        
		    }else{
		    	System.out.println("로그인 실패");
		        return "redirect:/views/login"; // 로그인 실패 시 다시 로그인 페이지로
		    }
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("예외 확인");
			return "redirect:/views/login";
		}
		
	}

	// 해당 등급별 이름 불러오기
	private String getGradeName(int exp) {
		String gradeName = "";
		
		if (exp < 20) {
		    gradeName = "시장 삐약이";
	    } else if (exp >= 20 && exp < 40) {
	        gradeName = "시장 왕초보";
	    } else if (exp >= 40 && exp < 60) {
	        gradeName = "시장 탐험가";
	    } else if (exp >= 60 && exp < 80) {
	        gradeName = "시장 지킴이";
	    } else if (exp >= 80) {
	        gradeName = "시장 개척자";
	    }

	    return gradeName;
	    
	}
	
	// 회원 계정 화면
	@GetMapping("/views/myPage")
	public String myPage(HttpSession session, Model model) {
	    String userId = (String) session.getAttribute("userId");
	    
	    // 사용자 정보를 가져와서 memberList로 모델에 추가
	    MemberDO user = memberDAO.getMember(userId);
	    model.addAttribute("memberList", user);

	    if (userId != null) {
	        // userId를 모델에 추가하여 마이페이지에서 사용 가능
	        model.addAttribute("userId", userId);
	        
	        // 등급 정보를 가져와 모델에 추가
	        int userGrade = user.getExp();
	        String gradeName = getGradeName(userGrade);
	        model.addAttribute("gradeName", gradeName);

	        // 내가 등록한 점포 개수 반환
	        StoreDAO storeDAO = new StoreDAO();
	        int storeCount = storeDAO.getStoreCount(userId);
	        model.addAttribute("storeCount", storeCount);
	        
	        // 내가 찜한 점포 개수 반환
	        int storeLikeCount = storeDAO.getStoreLikeCount(userId);
	        model.addAttribute("storeLikeCount", storeLikeCount);
	        
	        // 내가 등록한 리뷰 개수 반환
	        ReviewDAO reviewDAO = new ReviewDAO();
	        int reviewCount = reviewDAO.getReviewCount(userId);
	        model.addAttribute("reviewCount", reviewCount);
	        
	        // 내가 등록한 글 개수 반환
	        PostDAO postDAO = new PostDAO();
	        int postCount = postDAO.getPostCount(userId);
	        model.addAttribute("postCount", postCount);
	        
	        
	        
	        // 내가 등록한 모든 글에 대한 정보 조회
	        ArrayList<PostDO> postDOList = postDAO.getAllPostById(userId);
	        model.addAttribute("postDOList", postDOList);
	        
	        
	        // 내가 등록한 모든 리뷰에 대한 정보 조회
	        ArrayList<ReviewDO> reviewDOList = reviewDAO.getAllReviewById(userId);
	        model.addAttribute("reviewDOList", reviewDOList);
	        
	        
	        // 내가 등록한 모든 점포에 대한 정보 조회
	        ArrayList<StoreDO> storeDOInfoList = storeDAO.getAllInfoStoreById(userId);
	        model.addAttribute("storeDOInfoList", storeDOInfoList);
	        
	        
	        // 내가 찜한 모든 점포에 대한 정보 조회
	        ArrayList<StoreDO> storeDOLikeList = storeDAO.getAllLikeStoreById(userId);
	        model.addAttribute("storeDOLikeList", storeDOLikeList);

	        
	        
	        return "myPage"; // 마이페이지 뷰로 이동
	    } else {
	        // 로그인하지 않은 경우 처리
	        return "redirect:/views/login"; // 로그인 페이지로 리다이렉트
	    }
	}
	

	
	@GetMapping("/views/myPageUpdate") // URL 경로 변경
	public String toMyPageUpdate(HttpSession session, Model model) {
	    String userId = (String) session.getAttribute("userId");

	    // 사용자 정보를 가져와서 memberList로 모델에 추가
	    MemberDO user = memberDAO.getMember(userId);
	    model.addAttribute("memberList", user);

	    return "myPageUpdate"; // 비밀번호와 닉네임 수정 페이지로 이동
	}

	
//	@GetMapping("/updateNickname") // URL 경로 변경
//	public String updateNickname(@ModelAttribute MemberDO command, HttpSession session, Model model) {
//	    // 현재 사용자 아이디 가져오기
//	    String userId = (String) session.getAttribute("userId");
//	    command.setId(userId);
//
//	    try {
//	        memberDAO.changeNickname(command);
//	        // 닉네임 업데이트 성공 시, 세션 업데이트
//	        MemberDO updatedUser = memberDAO.getMember(userId);
//	        session.setAttribute("memberInfo", updatedUser);
//	    } catch (Exception e) {
//	        model.addAttribute("msg", e.getMessage());
//	        return "myPageUpdate"; // 업데이트 실패 시 수정 페이지로 다시 돌아감
//	    }
//
//	    return "redirect:/views/myPage"; // 업데이트 성공 시 마이페이지로 리다이렉트
//	}
//	
//	@PostMapping("/updatePasswd") // URL 경로 변경
//	public String updatePasswd(@ModelAttribute MemberDO command, HttpSession session, Model model) {
//	    // 현재 사용자 아이디 가져오기
//	    String userId = (String) session.getAttribute("userId");
//	    command.setId(userId);
//
//	    try {
//	        memberDAO.changePasswd(command);
//	        // 비밀번호 업데이트 성공 시, 세션 업데이트
//	        MemberDO updatedUser = memberDAO.getMember(userId);
//	        session.setAttribute("memberInfo", updatedUser);
//	    } catch (Exception e) {
//	        model.addAttribute("msg", e.getMessage());
//	        return "myPageUpdate"; // 업데이트 실패 시 수정 페이지로 다시 돌아감
//	    }
//
//	    return "redirect:/views/myPage"; // 업데이트 성공 시 마이페이지로 리다이렉트
//	}
	
	

	// 회원 탈퇴
	@PostMapping("/views/myPage/deleteMember")
	public String deleteMember(@RequestParam("id") String id, HttpSession session) {
		
		memberDAO.deleteMember(id);
		
		
		// 세션에서 사용자 정보 삭제
	    session.removeAttribute("userId");
	    session.invalidate();
	    
		return "redirect:/views/main";
	}
	
	
	// 에러 페이지로 이동
	@GetMapping("/views/error")
	public String toError(Model model) {
		return "error";
	}
}

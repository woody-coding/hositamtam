package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.MarketDAO;
import model.PaymentDO;
import model.StoreDAO;
import model.StoreDO;

@Controller
public class MarketAndStoreController {

	private MarketDAO marketDAO = new MarketDAO();
	private StoreDAO storeDAO = new StoreDAO();

	public MarketAndStoreController() {
	}

	public MarketAndStoreController(MarketDAO marketDAO) {
		this.marketDAO = marketDAO;
	}

	// 1. 시장 시장 리스트 페이지 - 파라미터 : [keyword] or [cateno]
	@GetMapping("/views/marketList")
	public String MarketList(@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "cateno", required = false) String cateno, Model model) {

		if (keyword == null && cateno == null) {
			model.addAttribute("msg", "잘못된 접근입니다. 시장명 검색 혹은 카테고리 선택을 통해 원하시는 전통시장을 찾아주세요!");
		}

		model.addAttribute("keyword", keyword);
		model.addAttribute("cateno", cateno);
		return "market";
	}

	// 2. 점포 리스트 페이지 - 파라미터 : mno
	@GetMapping("/views/store")
	public String StoreList(@RequestParam(value = "mno", required = false) String mno, Model model) {

		if (mno == null) {
			model.addAttribute("msg", "잘못된 접근입니다. 원하시는 전통시장을 검색 혹은 카테고리를 통해 선택해주세요!");
		}

		model.addAttribute("mno", mno);
		return "store";
	}

	// 점포 상세조회
	@GetMapping("/views/storeDetail")
	public String StoreDetail(@RequestParam int sno, Model model) {

		// 효철님 이거 빼니까 sno값 안넘어와서 넣어야할 거 같아요
		model.addAttribute("sno", sno);
		
		StoreDO storeDO = new StoreDO();
		storeDO.setSno(sno);

		// 점포 정보 조회
		storeDO = storeDAO.getStore(storeDO);
		model.addAttribute("store", storeDO);

		// 점포의 결제방식
		List<PaymentDO> storePaymentList = new ArrayList<PaymentDO>();
		storePaymentList = storeDAO.getStorePaymentList(storeDO);
		model.addAttribute("storePaymentList", storePaymentList);

		// 점포의 평점, 후기 개수
		StoreDO storeReviewAvg = storeDAO.getStoreReviewAvg(storeDO);
		model.addAttribute("storeReviewAvg", storeReviewAvg);
//		
//		// 점포리뷰 리스트
		List<StoreDO> storeReviewList = storeDAO.getStoreReviewList(storeDO);
		model.addAttribute("storeReviewList", storeReviewList);

		return "storeDetail";
	}

	@GetMapping("/views/storeInsert")
//	public String StoreInsert(@RequestParam StoreDO storeDO, Model model) { // joke
	public String StoreInsert(HttpServletRequest request) {
		System.out.println("joke - storeInsert");
		
		System.out.println(request.getParameterNames());
		System.out.println("slat : " + request.getParameter("slat"));
		System.out.println("slng : " + request.getParameter("slng"));
		// 수정예정
//		model.addAttribute("storeList", storeDAO.StoreUpdate(storeDO));
		return "storeInsertAndUpdate";
	}
	
	// 점포등록
	@PostMapping("/storeInsert")
	public String storeInsert(HttpServletRequest request, HttpSession session, Model model, @ModelAttribute StoreDO storeDO) {
	    // 현재 사용자 아이디 가져오기
	    String userId = (String) session.getAttribute("userId");
	    try {
	    	storeDO.setId(userId);
	    	System.out.println("시장[mno] : " 			+ storeDO.getMno());
	    	System.out.println("등록자[id] : " 			+ storeDO.getId());
	    	System.out.println("점포명[sno] : " 		+ storeDO.getSname());
	    	System.out.println("점포형태(stype] : " 	+ storeDO.getStype());
	    	System.out.println("결제방식[paytype] : " 	+ storeDO.getPaytype());
	    	System.out.println("취급품목[scategory] : " + storeDO.getScategory());
	    	System.out.println("점포사진[sphoto] : "	+ storeDO.getSphoto());
	    	
	    	String[] payType = storeDO.getPaytype().split(",");
	    	// 등록
	    	storeDAO.insertStore(storeDO, payType);
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
//	    return "redirect:/views/myPage"; // 점포 등록 후 마이페이지로 리다이렉트
	    return "redirect:/views/store?mno=" + storeDO.getMno();  // 점포 등록 후 시장 화면으로
	}

	@GetMapping("/views/storeUpdate")
	public String StoreUpdate(@RequestParam(value = "sno", required = false) String sno, Model model) {
		// 수정예정

//		model.addAttribute("storeList", storeDAO.StoreInsert(storeDO));
//		return "storeInsertAndUpdate";

		StoreDO storeDO = new StoreDO();
		storeDO.setSno(Integer.parseInt(sno));
		
		// 점포 정보 조회
		storeDO = storeDAO.getStore(storeDO);
		model.addAttribute("store", storeDO);
		
		// 점포의 결제방식
		List<PaymentDO> storePaymentList = new ArrayList<PaymentDO>();
		storePaymentList = storeDAO.getStorePaymentList(storeDO);
		model.addAttribute("storePaymentList", storePaymentList);
		
		// 결제방식 리스트
		List<PaymentDO> paymentList = new ArrayList<PaymentDO>();
		paymentList = storeDAO.getPaymentList();
		model.addAttribute("paymentList", paymentList);

		model.addAttribute("sno", sno);
		model.addAttribute("update", true);
		return "storeInsertAndUpdate"; // 수정화면

	}
	
	// 점포수정
	@PostMapping("/storeUpdate")
	public String storeUpdate(HttpServletRequest request, HttpSession session, Model model, @ModelAttribute StoreDO storeDO) {
	    // 현재 사용자 아이디 가져오기
	    String userId = (String) session.getAttribute("userId");
	    try {
	    	storeDO.setId(userId);
	    	System.out.println("시장[mno] : " 			+ storeDO.getMno());
	    	System.out.println("등록자[id] : " 			+ storeDO.getId());
	    	System.out.println("점포명[sno] : " 		+ storeDO.getSname());
	    	System.out.println("점포형태(stype] : " 	+ storeDO.getStype());
	    	System.out.println("결제방식[paytype] : " 	+ storeDO.getPaytype());
	    	System.out.println("취급품목[scategory] : " + storeDO.getScategory());
	    	System.out.println("점포사진[sphoto] : "	+ storeDO.getSphoto());
	    	
	    	String[] payType = storeDO.getPaytype().split(",");
	    	// 수정
	    	storeDAO.updateStore(storeDO, payType);
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
//	    return "redirect:/views/myPage"; // 업데이트 성공 시 마이페이지로 리다이렉트
	    return "redirect:/views/store?mno=" + storeDO.getMno();  // 점포수정 후 시장 화면으로
	}

}

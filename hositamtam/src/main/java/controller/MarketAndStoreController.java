package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import model.PostDAO;
import model.PostDO;
import model.ReviewDAO;
import model.ReviewDO;
import model.StoreDAO;
import model.StoreDO;

@Controller
public class MarketAndStoreController {

	private MarketDAO marketDAO = new MarketDAO();
	private StoreDAO storeDAO = new StoreDAO();
	private ReviewDAO reviewDAO = new ReviewDAO();

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
	// 등록 페이지로 이동
	@GetMapping("/views/toStoreInsert")
	public String StoreInsert(@RequestParam String id,@RequestParam String mno, @RequestParam String slat, @RequestParam String slng, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("mno", mno);
		model.addAttribute("slat", slat);
		model.addAttribute("slng", slng);
		return "storeInsert";
	}
	// 등록 기능
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
	public String storeInsert(HttpServletRequest request, HttpSession session) throws IOException {
	    // 현재 사용자 아이디 가져오기
	    String userId = (String) session.getAttribute("userId");
	    // sphoto가 비어있거나 공백이면 "testphoto2.png"를 대입
//        if (storeDO.getSphoto() == null || storeDO.getSphoto().trim().isEmpty()) {
//            storeDO.setSphoto("testphoto2.jpeg");
//        }
		
        String directory = "C:\\projects\\final\\hositamtam\\hositamtam\\src\\main\\webapp\\storePhotoUpload";
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
		String storePhoto = "/finalProject/storePhotoUpload/" + savedName;

		StoreDO store = new StoreDO();
		store.setId(userId);
		store.setMno(Integer.parseInt(multi.getParameter("mno")));;
		store.setSname(multi.getParameter("sname"));
		store.setSlat(multi.getParameter("slat"));
		store.setSlng(multi.getParameter("slng"));
		store.setStype(multi.getParameter("stype"));
		store.setSphoto(storePhoto);
		store.setScategory(multi.getParameter("scategory"));
		
		String[] paytypes = multi.getParameterValues("paytype");
		storeDAO.insertStore(store, paytypes);
	    return "redirect:/views/store?mno=" + store.getMno();
	}

	// 점포수정 준비
	// (store.jsp와 연결된) store.js에서 점포 수정을 누르면,
	// 점포정보를 불러와 storeInsertAndUpdate.jsp 화면에 띄움
	@GetMapping("/views/storeUpdate")
	public String StoreUpdate(@RequestParam(value = "sno", required = false) String sno,
		@RequestParam(value = "mno", required = false) String mno, Model model) {

		StoreDO storeDO = new StoreDO();
		storeDO.setSno(Integer.parseInt(sno));
		storeDO.setMno(Integer.parseInt(mno));
		
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
		model.addAttribute("mno", mno);
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
	    
	    return "redirect:/views/store?mno=" + storeDO.getMno();  // 점포수정 후 시장 화면으로
	}
	
	// 리뷰 등록
	@GetMapping("/views/reviewInsert")
	public String reviewInsert(@ModelAttribute ReviewDO command, Model model) {
		reviewDAO.insertReview(command);
		return "redirect:/views/storeDetail?sno=" + command.getSno();
	}
	// 리뷰 삭제
	@GetMapping("/views/deleteReview")
	public String deleteReview(@RequestParam int sno, @RequestParam int rno) {
		reviewDAO.deleteReview(rno);
		return "redirect:/views/storeDetail?sno=" + sno;
	}
}

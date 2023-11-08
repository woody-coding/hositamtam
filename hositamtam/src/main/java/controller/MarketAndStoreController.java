package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
//		List<StoreDO> storeReviewList = storeDAO.getStoreReviewList(storeDO);
//		model.addAttribute("storeReviewList", storeReviewList);

		return "storeDetail";
	}

	@GetMapping("/views/storeInsert")
	public String StoreInsert(@RequestParam StoreDO storeDO, Model model) {
		// 수정예정
//		model.addAttribute("storeList", storeDAO.StoreUpdate(storeDO));
		return "storeInsertAndUpdate";
	}

	@GetMapping("/views/storeUpdate")
	public String StoreUpdate(@RequestParam(value = "sno", required = false) String sno, Model model) {
		// 수정예정

//		model.addAttribute("storeList", storeDAO.StoreInsert(storeDO));
//		return "storeInsertAndUpdate";

		model.addAttribute("sno", sno);
		return "storeUpdate";

	}

}

package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.MarketDAO;
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
	public String MarketList(@RequestParam(value="keyword", required=false) String keyword, @RequestParam(value="cateno", required=false) String cateno, Model model) {
		
		if(keyword == null && cateno == null) {
			model.addAttribute("msg", "잘못된 접근입니다. 시장명 검색 혹은 카테고리 선택을 통해 원하시는 전통시장을 찾아주세요!");
		}
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("cateno", cateno);
		return "market";
	}

	// 2. 점포 리스트 페이지 - 파라미터 : mno
	@GetMapping("/views/store")
	public String StoreList(@RequestParam(value="mno", required=false) String mno, Model model) {
		
		if(mno == null) {
			model.addAttribute("msg", "잘못된 접근입니다. 원하시는 전통시장을 선택해주세요!");
		}
		
		model.addAttribute("mno", mno);
		return "store";
	}
	
	
	@GetMapping("/views/storeDetail")
	public String StoreDetail(@RequestParam(value="sno", required=false) String sno, Model model) {
		
		model.addAttribute("sno", sno);
		return "storeDetail";
	}
	@GetMapping("/views/storeInsert")
	public String StoreInsert(@RequestParam StoreDO storeDO, Model model) {
		//수정예정
//		model.addAttribute("storeList", storeDAO.insertStore(storeDO));
		return "storeList";
	}
	@GetMapping("/views/storeUpdate")
	public String StoreUpdate(@RequestParam StoreDO storeDO, Model model) {
		//수정예정
		model.addAttribute("storeList", storeDAO.updateStore(storeDO));
		return "storeList";
	}
	

}

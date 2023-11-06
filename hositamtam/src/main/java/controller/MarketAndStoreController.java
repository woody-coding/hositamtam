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
		model.addAttribute("keyword", keyword);
		model.addAttribute("cateno", cateno);
		return "market";
	}

	// 2. 점포 리스트 페이지 - 파라미터 : mno
	@GetMapping("/views/store")
	public String StoreList(@RequestParam(value="mno", required=false) String mno, Model model) {
		model.addAttribute("mno", mno);
		return "store";
	}
	
	
	@GetMapping("/views/storeDetail")
	public String StoreDetail(@RequestParam int sno, int mno, Model model) {
		//수정예정
//		model.addAttribute("storeList", storeDAO.getStoreDetail(mno, sno));
		return "storeList";
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

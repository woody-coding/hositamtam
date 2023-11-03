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
	// 시장 조회
	@GetMapping("/views/marketList")
	public String MarketList(Model model) {
		model.addAttribute("marketList", marketDAO.getMarketList());
		return "market";
	}
	@GetMapping("/views/marketByCategory")	
	public String MarketListByItem(@RequestParam String cateno, Model model) {
		model.addAttribute("marketList", marketDAO.getMarketListByItem(cateno));
		return "market";
	}
	@GetMapping("/views/marketBySearch")
	public String MarketListBySearch(@RequestParam String keyword, Model model) {
		model.addAttribute("marketList", marketDAO.getMarketListBySearch(keyword));
		return "market";
	}
	// 점포 조회
	@GetMapping("/views/store")
	public String StoreList(@RequestParam int mno, Model model) {
		//수정예정
		model.addAttribute("storeList", storeDAO.getStoreList(mno));
		return "storeList";
	}
	@GetMapping("/views/storeDetail")
	public String StoreDetail(@RequestParam int sno, int mno, Model model) {
		//수정예정
		model.addAttribute("storeList", storeDAO.getStoreDetail(mno, sno));
		return "storeList";
	}
	@GetMapping("/views/storeInsert")
	public String StoreInsert(@RequestParam StoreDO storeDO, Model model) {
		//수정예정
		model.addAttribute("storeList", storeDAO.insertStore(storeDO));
		return "storeList";
	}
	@GetMapping("/views/storeUpdate")
	public String StoreUpdate(@RequestParam StoreDO storeDO, Model model) {
		//수정예정
		model.addAttribute("storeList", storeDAO.updateStore(storeDO));
		return "storeList";
	}
	

}

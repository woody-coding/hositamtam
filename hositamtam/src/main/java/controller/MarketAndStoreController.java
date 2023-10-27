package controller;

import model.TestModel;

//
//import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import model.MarketDAO;
//import model.MarketDO;
/*
mark 
 

 */
@Controller
public class MarketAndStoreController {

//	private MarketDAO MarketDAO;
	
	public MarketAndStoreController() {
	}
//	
//	public MarketAndStoreController(MarketDAO MarketDAO) {
//		this.MarketDAO = MarketDAO;
//	}
	
	@GetMapping("/views/toMarket")
	public String Market(TestModel testModel) {
		testModel.setControllerMsg("현재 marketController에서 화면 구현 중");
		testModel.setNo(1);
		testModel.setName("부평시장");
		testModel.setType("상설장");
		return "market";
	}

	@GetMapping("/views/store")
	public String StoreList(@RequestParam String name ,TestModel testModel) {
		testModel.setName(name);
		testModel.setStoreName("꽃분이네");

		return "store";
	}

	@GetMapping("/views/storeDetail")
	public String StoreDetail(@RequestParam String name ,TestModel testModel) {
		testModel.setStoreName(name);
		
		return "storeDetail";
	}
	

}

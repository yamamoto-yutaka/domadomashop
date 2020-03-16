package jp.co.aivick.domashop.controller.stock;

import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.View;
import jp.co.aivick.domashop.dao.MenuDao;
import jp.co.aivick.domashop.dao.MenuDaoImpl;
import jp.co.aivick.domashop.dao.*;
import jp.co.aivick.domashop.entity.Menu;
import jp.co.aivick.domashop.entity.Recipe;
import jp.co.aivick.domashop.entity.Stock;
import org.seasar.doma.jdbc.tx.TransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockController {
    StockList stockList = new StockList();
View view = new View();
    @GetMapping("/")
    public String stock(Model model){
        view.view(model);
        return "stock/stock";
    }
    @PostMapping
    public String stockpost(Model model, @RequestParam("name")String[] name,@RequestParam("adjustment") String adjustment,@RequestParam("stock")String inv) {
        String result = stockList.comment(name, adjustment, inv);
        if (result.equals("ストックを登録しました。")) {
            stockList.stockupdate(name, adjustment, inv);
           view.view(model);
            model.addAttribute("stockmessage",result);
            return "stock/stock";
        }
        view.view(model);
        model.addAttribute("stockmessage",result);
        return "stock/stock";
    }

}

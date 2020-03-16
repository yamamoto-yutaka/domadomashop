package jp.co.aivick.domashop.controller.shop;

import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.View;
import jp.co.aivick.domashop.dao.StockDao;
import jp.co.aivick.domashop.dao.StockDaoImpl;
import jp.co.aivick.domashop.entity.Stock;
import org.seasar.doma.jdbc.tx.TransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/buy")
public class Buycontroller {
    View view = new View();
    Shop shop = new Shop();
    @GetMapping("/")
    public String buy(Model model){
        view.view(model);
        return "shop/buy";
    }
    @PostMapping
    public String buypost(Model model, @RequestParam("name")String[] name) {

        String buycomment = shop.comment(name);
        if (buycomment.equals("購入しました。")) {
            view.view(model);
            shop.shopbuy(name);
            model.addAttribute("buymassege", buycomment);
            return "shop/buy";
        }
        view.view(model);
        model.addAttribute("buymassege", buycomment);
        return "shop/buy";
    }

}

package jp.co.aivick.domashop.controller.mane;

import jp.co.aivick.domashop.View;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainController {
View view = new View();
    @GetMapping("/")
    public String top(Model model) {
        return "index";
    }
    @GetMapping("/recipe")
    public String recipe(Model model) {
        return "recipe/recipe";
    }
    @GetMapping("/recipelist")
    public String recipelist(Model model) {
        view.view(model);
        return "recipe/recipelist";
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        view.view(model);
        return "menu/menu";
    }

    @GetMapping("/menulist")
    public String menulist(Model model) {
        view.view(model);
        return "menu/menulist";
    }

    @GetMapping("/stock")
    public String stock(Model model) {
        view.view(model);
        return "stock/stock";
    }
    @GetMapping("/user")
    public String user(Model model) {
        view.view(model);
        return "shop/user";
    }
    @GetMapping("/buy")
    public String buy(Model model) {
        view.view(model);
        return "shop/buy";
    }

}


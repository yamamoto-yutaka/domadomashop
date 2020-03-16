package jp.co.aivick.domashop.controller.mane;

import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.View;
import jp.co.aivick.domashop.dao.MenuDao;
import jp.co.aivick.domashop.dao.RecipeDao;
import jp.co.aivick.domashop.dao.RecipeDaoImpl;
import jp.co.aivick.domashop.dao.MenuDaoImpl;
import jp.co.aivick.domashop.entity.Recipe;
import org.seasar.doma.jdbc.tx.TransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jp.co.aivick.domashop.entity.Menu;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
View view = new View();
MenuList menuList = new MenuList();
    @GetMapping("/")
    public String menu (Model model){
        view.view(model);
        return "menu/menu";
    }
    @PostMapping
    public String menupost(Model model, @RequestParam("menuname") String name,@RequestParam("kind") String kind,@RequestParam("price")String price
    ,@RequestParam("recipename")String[] arrayname){
        String resultcomment = menuList.comment(name,kind,price,arrayname);
        if(resultcomment.equals("メニューが登録できました。")){
            menuList.menuadd(name,kind,price,arrayname);
            view.view(model);
            model.addAttribute("menumessage",resultcomment);
            return "menu/menu";
        }
        view.view(model);
        model.addAttribute("menumessage",resultcomment);
        return "menu/menu";
    }
}

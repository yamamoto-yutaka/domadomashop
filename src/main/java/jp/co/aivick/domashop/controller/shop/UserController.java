package jp.co.aivick.domashop.controller.shop;

import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.View;
import jp.co.aivick.domashop.dao.UserDao;
import jp.co.aivick.domashop.dao.UserDaoImpl;
import jp.co.aivick.domashop.entity.User;
import org.seasar.doma.jdbc.tx.TransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    View view = new View();

    @GetMapping("/")
    public String user(Model model){
        view.view(model);
        return "shop/user";
    }

    @PostMapping
    public String userpost(Model model, @RequestParam("money")String money){
        if (money.equals("")){
            model.addAttribute("usermassege","入力してください。");
            view.view(model);
            return "shop/user";
        }
        int usermoney = 0;
        try {
            usermoney = Integer.parseInt(money);
        }catch (NumberFormatException e){
            model.addAttribute("usermassege","数字を入力してください。");
            view.view(model);
            return "shop/user";
        }
        if (usermoney < 0){
            model.addAttribute("usermassege","0以上の数字で入力してください。");
            view.view(model);
            return "shop/user";
        }
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        int finalUsermoney = usermoney;
        tm.required(() ->{
            User user = new User();
            UserDao dao = new UserDaoImpl();
            user.setMoney(finalUsermoney);
            dao.addmoney(user);
            view.view(model);
            model.addAttribute("usermassege","お金を追加しました。");
        });
        return "shop/user";
    }
}

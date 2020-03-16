package jp.co.aivick.domashop;

import jp.co.aivick.domashop.dao.*;
import jp.co.aivick.domashop.dao.RecipeDaoImpl;
import jp.co.aivick.domashop.entity.*;
import org.seasar.doma.jdbc.tx.TransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Controller
public class View {

    public void view(Model model) {
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        tm.required(() -> {
            MenuDao menudao = new MenuDaoImpl();
            List<Menu> menuList = menudao.menufindAll();
            RecipeDao recipedao = new RecipeDaoImpl();
            StockDao stockdao = new StockDaoImpl();
            List<Stock> stockList = stockdao.stockfindAll();
            List<Recipe> recipeList = recipedao.findAll();
            UserhistoryDao userhistoryDao = new UserhistoryDaoImpl();
            List<Userhistory> userhistories = userhistoryDao.historyfindAll();
            model.addAttribute("menuresults", menuList);
            model.addAttribute("results", recipeList);
            model.addAttribute("stocklist", stockList);
            int usermoneys = usermoney("ゲスト");
            model.addAttribute("moneys", usermoneys);
            int total = historymoney("ゲスト");
            model.addAttribute("historymoney",total);
            model.addAttribute("historymoney",total);
            model.addAttribute("userhistorys",userhistories);
        });
    }
    public int usermoney(String name){
        int usermoney = 0;
        List<User> users = new ArrayList<>();
        UserDao dao = new UserDaoImpl();
        users = dao.userfindAll();
        for (User i : users){
            if (i.getName().equals(name)){
                usermoney = i.getMoney();
            }
        }
        return usermoney;
    }
    public int historymoney(String name){
        int historymoney = 0;
        List<User> users = new ArrayList<>();
        UserDao dao = new UserDaoImpl();
        users = dao.userfindAll();
        for (User i : users){
            if (i.getName().equals(name)){
                historymoney = i.getHistorymoney();
            }
        }
        return historymoney;
    }

}

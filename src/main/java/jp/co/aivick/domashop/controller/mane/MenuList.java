package jp.co.aivick.domashop.controller.mane;

import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.dao.MenuDao;
import jp.co.aivick.domashop.dao.MenuDaoImpl;
import jp.co.aivick.domashop.entity.Menu;
import org.seasar.doma.jdbc.tx.TransactionManager;

import java.util.ArrayList;
import java.util.List;

public class MenuList {

    public void menuadd(String name, String kind , String price , String[] arrayname){
        String recipename= "";
        for (String i: arrayname) {
            recipename = recipename +i+" ";
        }
        int menuprice = Integer.valueOf(price);
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        Menu menu = new Menu();
        String finalRecipename = recipename;
        tm.required(() ->{
            MenuDao dao = new MenuDaoImpl();
            List<Menu> recipeList = dao.menufindAll();
            menu.name = name;
            menu.price = menuprice;
            menu.kind = kind;
            menu.recipename = finalRecipename;
            dao.menuinsert(menu);
        });
    }
    public String comment (String name, String kind , String price , String[] arrayname){
        Menu menu = new Menu();
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        List<String>resultList = new ArrayList<>();
        tm.required(() -> {
            MenuDao dao = new MenuDaoImpl();
            List<Menu> menuList = dao.menufindAll();
            for (Menu i: menuList) {
                resultList.add(i.getName());
            }
        });
        String comment;
        if (name.equals("") || kind.equals("") || price.equals("") || arrayname.equals("")) {
            comment = "空入力で登録できません。";
            return comment;
        }
        int resulrprice = 0;
        try {
            resulrprice = Integer.valueOf(price);
        } catch (NumberFormatException e) {
            comment = "価格には数字を入力してください。";
            return comment;
        }
        if (resulrprice < 0 ){
            comment = "0より小さい数は入力できません。";
            return comment;
        }
        for (int i = 0; i < resultList.size(); i++) {
            if (resultList.get(i).equals(name)) {
                comment = "同じレシピがあるため登録できません。";
                return comment;
            }
        }
        comment = "メニューが登録できました。";
        return comment;
        }
    }

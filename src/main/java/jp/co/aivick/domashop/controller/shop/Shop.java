package jp.co.aivick.domashop.controller.shop;

import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.dao.*;
import jp.co.aivick.domashop.dao.StockDaoImpl;
import jp.co.aivick.domashop.entity.Stock;
import jp.co.aivick.domashop.entity.User;
import jp.co.aivick.domashop.entity.Userhistory;
import org.seasar.doma.jdbc.tx.TransactionManager;
import jp.co.aivick.domashop.entity.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Shop {
History history = new History();
    private int usermoney;
    private int totalusermoney;
    private List<Stock> stockList = new ArrayList<>();
    public void shopbuy(String[]name){
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        tm.required(() -> {
                User user = new User();
                int total = totalmoney(name);
                UserDao userdao = new UserDaoImpl();
                Stock stock = new Stock();
                StockDao stockdao = new StockDaoImpl();
                user.setMoney(total);
                user.setHistorymoney(total);
                userdao.buyupdate(user);
                history.addhistory(name);
                for (String i : name) {
                    stock.setName(i);
                    stockdao.stockbuy(stock);
                }
        });
    }

    public int totalmoney(String[] name){
        int total = 0;
            Menu menu = new Menu();
            MenuDao dao = new MenuDaoImpl();
            List<Menu> menuList = dao.menufindAll();
            for (Menu i: menuList) {
                for (String j : name) {
                if (i.getName().equals(j)){
                    System.out.println(i.getName()+j);
                    total = total + i.getPrice();
                }
                }
            }
        return total;
    }

    public String comment (String[] name){
        User user = new User();
        UserDao dao = new UserDaoImpl();
                    String comment;
                    if (name.equals("")) {
                        comment = "空入力で購入はできません。";
                        return comment;
                    }
            TransactionManager tm = AppConfig.singleton().getTransactionManager();
            tm.required(() -> {
                List<User> users = dao.userfindAll();
                this.usermoney = 0;
                for (User i : users) {
                    if (i.getName().equals("ゲスト")) {
                        this.usermoney = i.getMoney();
                    }
                }
            });
            this.totalusermoney = 0;
            tm.required(() ->{
                this.totalusermoney = totalmoney(name);
            });
                    if (this.usermoney < this.totalusermoney) {
                        comment = "購入金額がたりません。";
                        return comment;
                    }

                    if (!(stokccheck(name))) {
                        comment = "在庫がありません。";
                        return comment;
                    }
                    comment = "購入しました。";
        return comment;

    }

    public boolean stokccheck(String[]name){
        Stock stock = new Stock();
        StockDao dao = new StockDaoImpl();
        this.stockList.clear();
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        tm.required(() -> {
            this.stockList = dao.stockfindAll();
        });
        for (Stock i : stockList){
            for (String j : name){
                if (i.getName().equals(j)){
                    if (i.getIvn()-1 < 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

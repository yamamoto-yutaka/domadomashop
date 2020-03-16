package jp.co.aivick.domashop.controller.stock;

import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.View;
import jp.co.aivick.domashop.dao.MenuDao;
import jp.co.aivick.domashop.dao.MenuDaoImpl;
import jp.co.aivick.domashop.dao.StockDao;
import jp.co.aivick.domashop.dao.StockDaoImpl;
import jp.co.aivick.domashop.entity.Menu;
import jp.co.aivick.domashop.entity.Stock;
import org.seasar.doma.jdbc.tx.TransactionManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockList {
    View view = new View();

    public void stockchange(String name, int ivn) {
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        tm.required(() -> {
            Stock stock = new Stock();
            StockDao dao = new StockDaoImpl();
            stock.name = name;
            stock.inv = Integer.valueOf(ivn);
            dao.stockchange(stock);
        });
    }

    public void stockadd(String name, int ivn) {
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        tm.required(() -> {
            System.out.println("!!");
            Stock stock = new Stock();
            StockDao dao = new StockDaoImpl();
            stock.name = name;
            stock.inv = Integer.valueOf(ivn);
            dao.stockinsert(stock);
        });
    }

    public void stockupdate(String[] name,String adjustment ,String ivn) {
        int stockivn = Integer.valueOf(ivn);
        if (adjustment.equals("sub")){
            stockivn = stockivn - stockivn - stockivn;
        }
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        int finalStockivn = stockivn;
        tm.required(() -> {
            StockDao dao = new StockDaoImpl();
            Map<String ,Integer> stockmap = new HashMap<>();
            List<Stock> stocks = dao.stockfindAll();
            for (Stock i : stocks){
                stockmap.put(i.getName(),i.getIvn());
            }
            for (String i : name){
                if (stockmap.containsKey(i)){
                   stockchange(i,finalStockivn);
                }else {
                    stockadd(i,finalStockivn);
                }
            }

        });
    }

    public String comment(String[] name, String adjust, String ivn) {
        String comment;
        if (name.equals("") || adjust.equals("") || ivn.equals("")) {
            comment = "ストックは空入力できません。";
            return comment;
        }
        int resultivn = 0;
        try {
            resultivn = Integer.valueOf(ivn);
        } catch (NumberFormatException e) {
            comment = "ストックは数字を入力してください。";
            return comment;
        }
        if (resultivn < 0) {
            comment = "ストックは0より小さい数は入力できません";
        }
        if (adjust.equals("sub")) {
            resultivn = resultivn - resultivn - resultivn;
            Map<String ,Integer> stockmap = new HashMap<>();
            TransactionManager tm = AppConfig.singleton().getTransactionManager();
            tm.required(() -> {
                StockDao dao = new StockDaoImpl();
                List<Stock> stocks = dao.stockfindAll();
                for (Stock i : stocks) {
                    stockmap.put(i.getName(), i.getIvn());
                }
            });
            for (String i : name){
             if (!(stockmap.containsKey(i))) {
                 comment = "ストックを0以下で入力できません。";
                 return comment;
             }
                 if (stockmap.get(i) + resultivn < 0){
                     comment = "ストックを0以下で入力できません。";
                     return comment;
                 }
             }
        }
       comment = "ストックを登録しました。";
        return comment;
    }
}


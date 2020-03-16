package jp.co.aivick.domashop.controller.shop;

import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.dao.UserhistoryDao;
import jp.co.aivick.domashop.dao.UserhistoryDaoImpl;
import jp.co.aivick.domashop.entity.Userhistory;
import org.seasar.doma.jdbc.tx.TransactionManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class History {

    public void addhistory(String[] name){
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        tm.required(() -> {
            Userhistory userhistory = new Userhistory();
            UserhistoryDao dao = new UserhistoryDaoImpl();
            List<Userhistory> historyList = dao.historyfindAll();
            Map<String,Integer> historymap = new HashMap<>();
            for (Userhistory i : historyList){
                historymap.put(i.getName(),i.getNumber());
            }
            for (String i : name){
                if (historymap.containsKey(i)){
                    userhistory.setName(i);
                    dao.historybuy(userhistory);
                }else {
                    userhistory.name = i;
                    userhistory.number = 1;
                    userhistory.username = "ゲスト";
                    dao.historyinsert(userhistory);
                }
            }

        });
        }
    }


package jp.co.aivick.domashop.dao;

import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.entity.Userhistory;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import java.util.List;


@Dao(config = AppConfig.class)
public interface UserhistoryDao{
    @Select
    List<Userhistory> historyfindAll();
    @Insert
    int historyinsert(Userhistory userhistory);
    @Update(sqlFile = true)
    int historybuy(Userhistory userhistory);
}

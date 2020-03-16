package jp.co.aivick.domashop.dao;

import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.entity.Stock;
import jp.co.aivick.domashop.entity.User;
import jp.co.aivick.domashop.entity.Userhistory;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import java.util.List;

@Dao(config = AppConfig.class)
public interface UserDao {
    @Select
    List<User> userfindAll();
    @Insert
    int Userinsert(User user);
    @Update(sqlFile = true)
    int addmoney(User user);
    @Update(sqlFile = true)
    int buyupdate(User user);

}

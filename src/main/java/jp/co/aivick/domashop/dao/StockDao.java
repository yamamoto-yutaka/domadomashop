package jp.co.aivick.domashop.dao;
import jp.co.aivick.domashop.entity.Stock;
import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.entity.Recipe;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

import java.util.List;
@Dao(config = AppConfig.class)
public interface StockDao {
    @Select
    List<Stock> stockfindAll();
        @Insert
        int stockinsert(Stock stock);
        @Update(sqlFile = true)
        int stockbuy(Stock stock);
        @Update(sqlFile = true)
    int stockchange(Stock stock);

}

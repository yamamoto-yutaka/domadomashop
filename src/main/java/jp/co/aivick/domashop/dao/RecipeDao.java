package jp.co.aivick.domashop.dao;

import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.entity.Recipe;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;

import java.util.List;

@Dao(config = AppConfig.class)
public interface RecipeDao {
    @Select
    List<Recipe> findAll();
    @Insert
    int insert(Recipe recipe);
}

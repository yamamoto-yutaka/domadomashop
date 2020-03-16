package jp.co.aivick.domashop.controller.recipe;

import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.dao.RecipeDao;
import jp.co.aivick.domashop.dao.RecipeDaoImpl;
import jp.co.aivick.domashop.entity.Recipe;
import org.seasar.doma.jdbc.tx.TransactionManager;

import java.util.ArrayList;
import java.util.List;

public class RecipeList {

//    public boolean recipecheck(String name){
//        for (int i = 0; i < recipeList.size() ; i++) {
//            if (recipeList.get(i).getName().equals(name)){
//                return false;
//            }
//        }
//        return true;
//    }

    public String comment(String name, String cal){
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        List<String>resultList = new ArrayList<>();
        tm.required(() -> {
            RecipeDao dao = new RecipeDaoImpl();
            List<Recipe> recipeList = dao.findAll();
            for (Recipe i : recipeList){
                resultList.add(i.getName());
            }
        });
            String comment;
            int calorie = 0;
            if (name.equals("") || cal.equals("")) {
                comment = "空入力で登録できません。";
                return comment;
            }
            try {
                 calorie = Integer.valueOf(cal);
            } catch (NumberFormatException e) {
                comment = "数字を入力してください。";
                return comment;
            }
            if (calorie < 0 ){
                comment = "0より小さい数は入力できません。";
                return comment;
            }

            for (int i = 0; i < resultList.size(); i++) {
                if (resultList.get(i).equals(name)) {
                    comment = "同じレシピがあるため登録できません。";
                    return comment;
                }
            }
            comment = "レシピを登録しました。";
            return comment;

        }

    public void recipeadd(String name,int cal){
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        Recipe recipe = new Recipe();
        tm.required(() ->{
            RecipeDao dao = new RecipeDaoImpl();
            List<Recipe> recipeList = dao.findAll();
            recipe.name = name;
            recipe.cal = cal;
            dao.insert(recipe);
        });
    }

    public int totalcalorie(List<String> name){
        RecipeDao dao = new RecipeDaoImpl();
        List<Recipe> recipeList = dao.findAll();
        int total = 0;
        for (Recipe i: recipeList) {
            for (String j: name) {
                if (i.getName().equals(j)){
                    total += i.getCalorie();
                }
            }
        }
        return total;
    }

}

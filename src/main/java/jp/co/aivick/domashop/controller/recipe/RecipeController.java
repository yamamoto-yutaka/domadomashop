package jp.co.aivick.domashop.controller.recipe;

import jp.co.aivick.domashop.AppConfig;
import jp.co.aivick.domashop.dao.RecipeDao;
import jp.co.aivick.domashop.dao.RecipeDaoImpl;
import jp.co.aivick.domashop.entity.Recipe;
import org.seasar.doma.jdbc.tx.TransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@RequestMapping("/recipe")
public class RecipeController {
    RecipeList recipelist = new RecipeList();
    @GetMapping("/")
    public String recipe(Model model){
        TransactionManager tm = AppConfig.singleton().getTransactionManager();
        tm.required(() ->{
            RecipeDao dao = new RecipeDaoImpl();
            List<Recipe> recipeList = dao.findAll();
            model.addAttribute("results",recipeList);
        });
        return "recipe/recipe";
    }
    @PostMapping
    public String recipepost(Model model, @RequestParam("recipename") String name, @RequestParam("recipecalorie") String cal){
        int calorie = 0;
        String check  = recipelist.comment(name,cal);
        if (check.equals("レシピを登録しました。")){
            calorie = Integer.valueOf(cal);
            recipelist.recipeadd(name,calorie);
            model.addAttribute("recipemassege",check);
            recipe(model);
            return "recipe/recipe";
        }
        recipe(model);
       model.addAttribute("recipemassege",check);
        return "recipe/recipe";
    }
}

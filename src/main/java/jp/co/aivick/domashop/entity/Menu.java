package jp.co.aivick.domashop.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public String kind;
    public Integer price;
    public String recipename;
    public String dept_id;
    public Menu(){
        this.name = name;
        this.kind = kind;
        this.price = price;
        this.recipename = recipename;
        this.dept_id = dept_id;
    }
    public String getName(){
        return this.name;
    }
    public String getKind(){
        return this.kind;
    }
    public Integer getPrice(){
        return this.price;
    }
    public String getRecipename(){
        return this.recipename;
    }
}

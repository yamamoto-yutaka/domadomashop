package jp.co.aivick.domashop.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public Integer inv;
    public String dept_id;

    public Stock(){
        this.name = name;
        this.inv = inv;
    }
    public String getName(){
        return this.name;
    }
    public int getIvn(){
        return this.inv;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setIvn(int stock){

        this.inv = stock;
    }

}


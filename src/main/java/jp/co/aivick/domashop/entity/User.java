package jp.co.aivick.domashop.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Integer money;
    public Integer historymoney;
    public String name;

    public User(){
        this.money = money;
    }
    public Integer getMoney(){
        return this.money;
    }
    public String getName(){
        return this.name;
    }
    public Integer getHistorymoney(){
        return this.historymoney;
    }

    public void setMoney(int money){
    this.money = money;
    }
    public void setHistorymoney(int historymoney){
        this.historymoney=historymoney;
    }
    public void setName(String name){
        this.name = name;
    }
}
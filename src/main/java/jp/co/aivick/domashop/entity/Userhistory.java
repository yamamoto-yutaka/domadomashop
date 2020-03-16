package jp.co.aivick.domashop.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;


@Entity
public class Userhistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public Integer number;
    public String username;
    public Userhistory(){
        this.name = name;
        this.number = number;
        this.username = username;
    }
    public String getName(){
        return this.name;
    }
    public Integer getNumber(){
        return this.number;
    }
    public String getUsername(){return this.username;}

    public void setNumber(int number){
        this.number=number;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setUsername(String username){this.username = username;}
//    public void setHistorynumber(int number){
//        this.historynumber = number;
//    }
}

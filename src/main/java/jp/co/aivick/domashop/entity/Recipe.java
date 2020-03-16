package jp.co.aivick.domashop.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;

@Entity
public class Recipe {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        public Integer id;
        public String name;
        public Integer cal;
        public String dept_id;

        public Recipe(){
                this.name = name;
                this.cal = cal;
        }
        public String getName(){
                return this.name;
        }
        public int getCalorie(){
                return this.cal;
        }


}

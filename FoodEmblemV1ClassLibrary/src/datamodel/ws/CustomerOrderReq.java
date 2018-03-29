/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.OrderDish;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author JiongYi
 */
@XmlRootElement
@XmlType(name = "customerOrderReq", propOrder = {
    "email" , "orderdishes" , "total"
})
public class CustomerOrderReq implements Serializable{
    private String email;
    private List<OrderDish>orderdishes;
    private Double total;
    
    public CustomerOrderReq(){
        
    }
    
    public CustomerOrderReq(String email, List<OrderDish>orderdishes,Double total){
        this.email = email;
        this.orderdishes = orderdishes;
        this.total = total;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the orderdishes
     */
    public List<OrderDish> getOrderdishes() {
        return orderdishes;
    }

    /**
     * @param orderdishes the orderdishes to set
     */
    public void setOrderdishes(List<OrderDish> orderdishes) {
        this.orderdishes = orderdishes;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }
}

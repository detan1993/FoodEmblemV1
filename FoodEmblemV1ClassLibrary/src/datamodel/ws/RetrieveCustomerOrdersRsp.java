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
@XmlType(name = "retrieveCustomerOrdersRsp", propOrder = {
    "customerOrders"
})
public class RetrieveCustomerOrdersRsp implements Serializable {
    
    private List<OrderDish> customerOrders;
    
    
    public RetrieveCustomerOrdersRsp(){
        
    }
    
    public RetrieveCustomerOrdersRsp(List<OrderDish> customerOrders){
        this.customerOrders = customerOrders;
    }

    /**
     * @return the customerOrders
     */
    public List<OrderDish> getCustomerOrders() {
        return customerOrders;
    }

    /**
     * @param customerOrders the customerOrders to set
     */
    public void setCustomerOrders(List<OrderDish> customerOrders) {
        this.customerOrders = customerOrders;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.RestaurantCustomerOrder;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */
@XmlRootElement
@XmlType(name = "customerOrderRsp", propOrder = {
    "customerorder"
})

public class CustomerOrderRsp  implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    private RestaurantCustomerOrder customerorder;

    public CustomerOrderRsp() {
    }
    
    
    public CustomerOrderRsp(RestaurantCustomerOrder customerorder) {
        this.customerorder = customerorder;
    }

    /**
     * @return the customerorder
     */
    public RestaurantCustomerOrder getCustomerorder() {
        return customerorder;
    }

    /**
     * @param customerorder the customerorder to set
     */
    public void setCustomerorder(RestaurantCustomerOrder customerorder) {
        this.customerorder = customerorder;
    }

   
    
    
}

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
@XmlType(name = "CustomerOrderRsp", propOrder = {
    "restCustOrder"
})

public class CustomerOrderRsp  implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    private RestaurantCustomerOrder restCustOrder;

    public CustomerOrderRsp() {
    }
    
    
    public CustomerOrderRsp(RestaurantCustomerOrder restCustOrder) {
        this.restCustOrder = restCustOrder;
    }
 
    /**
     * @return the customerorder
     */
    public RestaurantCustomerOrder getCustomerorder() {
        return restCustOrder;
    }

    /**
     * @param restCustOrder the restCustOrder to set
     */
    public void setCustomerorder(RestaurantCustomerOrder restCustOrder) {
        this.restCustOrder = restCustOrder;
    }

   
    
    
}

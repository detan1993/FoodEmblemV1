/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author shanw
 */

@XmlRootElement
@XmlType(name = "updateRestaurantCustomerOrderCookedReq", propOrder = {
   "orderId"
})
public class UpdateRestaurantCustomerOrderCookedReq {
    private long orderId;

    public UpdateRestaurantCustomerOrderCookedReq() {
    }

    public UpdateRestaurantCustomerOrderCookedReq(long orderId) {
        this.orderId = orderId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    
}

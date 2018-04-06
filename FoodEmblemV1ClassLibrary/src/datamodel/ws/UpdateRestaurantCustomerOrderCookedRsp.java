/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author shanw
 */
@XmlRootElement
@XmlType(name = "updateRestaurantCustomerOrderCookedRsp", propOrder = {
    "isUpdated"
})


public class UpdateRestaurantCustomerOrderCookedRsp implements Serializable{
    private boolean isUpdated;

    public UpdateRestaurantCustomerOrderCookedRsp() {
    }

    public UpdateRestaurantCustomerOrderCookedRsp(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public boolean isIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }
    
    
}

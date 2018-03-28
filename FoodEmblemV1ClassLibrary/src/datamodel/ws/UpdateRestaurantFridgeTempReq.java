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
 * @author David
 */

@XmlRootElement
@XmlType(name = "updateRestaurantFridgeTempReq", propOrder = {
   "restaurantId" , "fridgeId" , "tempValue"
})

public class UpdateRestaurantFridgeTempReq implements Serializable  {
  
    private static final long serialVersionUID = 1L;
    
    private long restaurantId;
    private long fridgeId;
    private double tempValue;

    public UpdateRestaurantFridgeTempReq() {
    }

    public UpdateRestaurantFridgeTempReq(long restaurantId, long fridgeId, double tempValue) {
        this.restaurantId = restaurantId;
        this.fridgeId = fridgeId;
        this.tempValue = tempValue;
    }

    
    /**
     * @return the restaurantId
     */
    public long getRestaurantId() {
        return restaurantId;
    }

    /**
     * @param restaurantId the restaurantId to set
     */
    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * @return the fridgeId
     */
    public long getFridgeId() {
        return fridgeId;
    }

    /**
     * @param fridgeId the fridgeId to set
     */
    public void setFridgeId(long fridgeId) {
        this.fridgeId = fridgeId;
    }

    /**
     * @return the tempValue
     */
    public double getTempValue() {
        return tempValue;
    }

    /**
     * @param tempValue the tempValue to set
     */
    public void setTempValue(double tempValue) {
        this.tempValue = tempValue;
    }
    
    

   
}

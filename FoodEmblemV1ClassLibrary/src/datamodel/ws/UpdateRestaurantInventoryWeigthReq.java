/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.Inventory;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */

@XmlRootElement
@XmlType(name = "updateRestaurantInventoryWeigthReq", propOrder = {
   "restaurantId" , "containerId" , "inventoryWeight"
})

public class UpdateRestaurantInventoryWeigthReq  implements Serializable  {
  
    private static final long serialVersionUID = 1L;
    
    private long restaurantId;
    private long containerId;
  //  private Inventory inventory;
    private double inventoryWeight;

    public UpdateRestaurantInventoryWeigthReq() {
    }

    public UpdateRestaurantInventoryWeigthReq(long restaurantId, long containerId, double inventoryWeight) {
        this.restaurantId = restaurantId;
        this.containerId = containerId;
        this.inventoryWeight = inventoryWeight;
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
     * @return the containerId
     */
    public long getContainerId() {
        return containerId;
    }

    /**
     * @param containerId the containerId to set
     */
    public void setContainerId(long containerId) {
        this.containerId = containerId;
    }

    /**
     * @return the inventoryWeight
     */
    public double getInventoryWeight() {
        return inventoryWeight;
    }

    /**
     * @param inventoryWeight the inventoryWeight to set
     */
    public void setInventoryWeight(double inventoryWeight) {
        this.inventoryWeight = inventoryWeight;
    }
     
}

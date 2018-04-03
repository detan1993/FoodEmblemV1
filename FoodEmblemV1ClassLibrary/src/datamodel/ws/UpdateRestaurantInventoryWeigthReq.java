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
   "restaurantId" , "containerId" , "inventory"
})

public class UpdateRestaurantInventoryWeigthReq  implements Serializable  {
  
    private static final long serialVersionUID = 1L;
    
    private long restaurantId;
    private long containerId;
    private Inventory inventory;

    public UpdateRestaurantInventoryWeigthReq() {
    }

    public UpdateRestaurantInventoryWeigthReq(long restaurantId, long containerId, Inventory inventory) {
        this.restaurantId = restaurantId;
        this.containerId = containerId;
        this.inventory = inventory;
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
     * @return the inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * @param inventory the inventory to set
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    
    
}

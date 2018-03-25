/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.Fridge;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */

@XmlRootElement
@XmlType(name = "retrieveRestaurantFridgeRsp", propOrder = {
    "fridges"
})

public class RetrieveRestaurantFridgeRsp implements Serializable {
    
     private static final long serialVersionUID = 1L;
     
     private List<Fridge> fridges;

    public RetrieveRestaurantFridgeRsp() {
    }

    public RetrieveRestaurantFridgeRsp(List<Fridge> fridges) {
        this.fridges = fridges;
    }

    
    /**
     * @return the fridges
     */
    public List<Fridge> getFridges() {
        return fridges;
    }

    /**
     * @param fridges the fridges to set
     */
    public void setFridges(List<Fridge> fridges) {
        this.fridges = fridges;
    }

     
    
    
    
}
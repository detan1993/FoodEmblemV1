/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.Restaurant;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */

@XmlRootElement
@XmlType(name = "retrieveRestaurantRsp", propOrder = {
    "restaurants" 
})

public class RetrieveRestaurantsRsp implements Serializable {
    
        private static final long serialVersionUID = 1L;
    
    private List<Restaurant> restaurants;

    public RetrieveRestaurantsRsp() {
    }

    public RetrieveRestaurantsRsp(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    /**
     * @return the restaurants
     */
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    /**
     * @param restaurants the restaurants to set
     */
    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.RestaurantSeating;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author shanw
 */
@XmlRootElement
@XmlType(name = "retrieveRestaurantSeatingActiveRsp", propOrder = {
    "restaurantSeatings"
})

public class RetrieveRestaurantSeatingActiveRsp {
    private List<Integer> restaurantSeatings;

    public RetrieveRestaurantSeatingActiveRsp() {
    }

    public RetrieveRestaurantSeatingActiveRsp(List<Integer> restaurantSeatings) {
        this.restaurantSeatings = restaurantSeatings;
    }

    public List<Integer> getRestaurantSeatings() {
        return restaurantSeatings;
    }

    public void setRestaurantSeatings(List<Integer> restaurantSeatings) {
        this.restaurantSeatings = restaurantSeatings;
    }

    
    
}

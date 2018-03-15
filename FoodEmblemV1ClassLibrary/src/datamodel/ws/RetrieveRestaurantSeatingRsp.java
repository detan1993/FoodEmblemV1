/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.RestaurantSeating;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */

    @XmlRootElement
@XmlType(name = "retrieveRestaurantSeatingRsp", propOrder = {
    "seats"
})

public class RetrieveRestaurantSeatingRsp implements Serializable {
    
        
    private static final long serialVersionUID = 1L;
    
    private List<RestaurantSeating> seats;

    public RetrieveRestaurantSeatingRsp() {
    }

    public RetrieveRestaurantSeatingRsp(List<RestaurantSeating> seats) {
        this.seats = seats;
    }
    
    /**
     * @return the seats
     */
    public List<RestaurantSeating> getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(List<RestaurantSeating> seats) {
        this.seats = seats;
    }
    
    

}

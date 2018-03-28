/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.Reservation;
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
@XmlType(name = "retrieveCustomerReservationsRsp", propOrder = {
    "custReservation","restNames" , "restaurantSeating"
})

public class RetrieveCustomerReservationsRsp  implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    private List<Reservation>custReservation;
    private List<String>restNames;
    private RestaurantSeating restaurantSeating;
   
    public RetrieveCustomerReservationsRsp() {
    }
    
    public RetrieveCustomerReservationsRsp(RestaurantSeating restaurantSeating){
        this.restaurantSeating = restaurantSeating;
    }
    
    public RetrieveCustomerReservationsRsp(List<Reservation>custReservation,List<String>restNames){
        this.custReservation = custReservation;
        this.restNames = restNames;
    }

    /**
     * @return the custReservation
     */
    public List<Reservation> getCustReservation() {
        return custReservation;
    }

    /**
     * @param custReservation the custReservation to set
     */
    public void setCustReservation(List<Reservation> custReservation) {
        this.custReservation = custReservation;
    }

    /**
     * @return the restNames
     */
    public List<String> getRestNames() {
        return restNames;
    }

    /**
     * @param restNames the restNames to set
     */
    public void setRestNames(List<String> restNames) {
        this.restNames = restNames;
    }

    /**
     * @return the restaurantSeating
     */
    public RestaurantSeating getRestaurantSeating() {
        return restaurantSeating;
    }

    /**
     * @param restaurantSeating the restaurantSeating to set
     */
    public void setRestaurantSeating(RestaurantSeating restaurantSeating) {
        this.restaurantSeating = restaurantSeating;
    }
    
}

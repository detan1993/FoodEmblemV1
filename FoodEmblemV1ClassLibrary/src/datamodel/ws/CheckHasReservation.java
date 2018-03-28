/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.Reservation;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
/**
 *
 * @author JiongYi
 */
@XmlRootElement
@XmlType(name = "checkHasReservation", propOrder = { 
    "hasReservation"
})
public class CheckHasReservation {
    private Boolean hasReservation;
    
    public CheckHasReservation(){
        
    }
    
    public CheckHasReservation(Boolean hasReservation){
        this.hasReservation = hasReservation;
    }

    /**
     * @return the hasReservation
     */
    public Boolean getHasReservation() {
        return hasReservation;
    }

    /**
     * @param hasReservation the hasReservation to set
     */
    public void setHasReservation(Boolean hasReservation) {
        this.hasReservation = hasReservation;
    }
}

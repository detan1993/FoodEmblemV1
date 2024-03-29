/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.Reservation;
import entity.Sensor;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */
@XmlRootElement
@XmlType(name = "reservationRsp", propOrder = {
    "reservation" , "reservationSeating", "finishsuccess"
})

public class ReservationRsp  implements Serializable  {
    private static final long serialVersionUID = 1L;
    
    private Reservation reservation;
    private Sensor reservationSeating;
    private Boolean finishsuccess;

    public ReservationRsp() {
    }
    
    public ReservationRsp(Sensor reservationSeating){
        this.reservationSeating = reservationSeating;
    }
    
    public ReservationRsp(Reservation reservation) {
        this.reservation = reservation;
    }
    public ReservationRsp(Boolean finishsuccess){
        this.finishsuccess = finishsuccess;
    }

    /**
     * @return the reservation
     */
    public Reservation getReservation() {
        return reservation;
    }

   
    /**
     * @param reservation the reservation to set
     */
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    /**
     * @return the reservationSeating
     */
    public Sensor getReservationSeating() {
        return reservationSeating;
    }

    /**
     * @param reservationSeating the reservationSeating to set
     */
    public void setReservationSeating(Sensor reservationSeating) {
        this.reservationSeating = reservationSeating;
    }

    /**
     * @return the finishsuccess
     */
    public Boolean getFinishsuccess() {
        return finishsuccess;
    }

    /**
     * @param finishsuccess the finishsuccess to set
     */
    public void setFinishsuccess(Boolean finishsuccess) {
        this.finishsuccess = finishsuccess;
    }
    
    
}

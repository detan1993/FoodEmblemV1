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
@XmlType(name = "reservationReq", propOrder = {
    "email" , "noOfPax" , "seatId"
})

public class ReservationReq  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String email;
    private int noOfPax;
    private long seatId;

    public ReservationReq() {
    }

    public ReservationReq(String email, int noOfPax , long seatId) {
        this.email = email;
        this.noOfPax = noOfPax;
        this.seatId = seatId;
    }
    

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the noOfPax
     */
    public int getNoOfPax() {
        return noOfPax;
    }

    /**
     * @param noOfPax the noOfPax to set
     */
    public void setNoOfPax(int noOfPax) {
        this.noOfPax = noOfPax;
    }

    /**
     * @return the seatId
     */
    public long getSeatId() {
        return seatId;
    }

    /**
     * @param seatId the seatId to set
     */
    public void setSeatId(long seatId) {
        this.seatId = seatId;
    }


   
}

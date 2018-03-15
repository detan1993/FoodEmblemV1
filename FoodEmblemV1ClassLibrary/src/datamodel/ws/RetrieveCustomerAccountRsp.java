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
@XmlType(name = "retrieveRestaurantSensorReq", propOrder = {
    "email" , "fullName" 
})

public class RetrieveCustomerAccountRsp implements Serializable {
    
     
    private static final long serialVersionUID = 1L;
    
    //private boolean status;
    private String email;
    private String fullName;

    public RetrieveCustomerAccountRsp() {
    }

    public RetrieveCustomerAccountRsp(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
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
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    

   
    
}

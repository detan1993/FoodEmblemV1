/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.RestaurantEmployee;
import java.io.Serializable;
import static javax.ws.rs.core.Response.status;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */


@XmlRootElement
@XmlType(name = "retrieveRestaurantEmployeeRsp", propOrder = {
    "email" , "fullName" , "restaurantId" , "gender" , "restaurantRole"
})
public class RetrieveEmployeeAccountRsp implements Serializable  {
    
    
    private static final long serialVersionUID = 1L;
    
    //private RestaurantEmployee employee;
    
    private String email;
    private String fullName;
    private String restaurantId;
    private char gender;
    private String restaurantRole;

    public RetrieveEmployeeAccountRsp() {
    }

    public RetrieveEmployeeAccountRsp(String email, String fullName, String restaurantId , char gender, String restaurantRole) {
        this.email = email;
        this.fullName = fullName;
        this.restaurantId = restaurantId;
        this.gender = gender;
        this.restaurantRole = restaurantRole;
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

    /**
     * @return the restaurantId
     */
    public String getRestaurantId() {
        return restaurantId;
    }

    /**
     * @param restaurantId the restaurantId to set
     */
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * @return the gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * @return the restaurantRole
     */
    public String getRestaurantRole() {
        return restaurantRole;
    }

    /**
     * @param restaurantRole the restaurantRole to set
     */
    public void setRestaurantRole(String restaurantRole) {
        this.restaurantRole = restaurantRole;
    }

  
  

    


   
    
    
    
}

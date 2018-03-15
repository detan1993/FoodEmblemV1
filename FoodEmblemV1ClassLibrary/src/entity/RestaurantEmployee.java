/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author David
 */
@Entity
public class RestaurantEmployee implements Serializable {

    private static final long serialVersionUID = 1L;
    
/*    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private char gender;
   
  //  @Column(columnDefinition = "CHAR(32) NOT NULL")
    private String password;
  //  @Column(columnDefinition = "CHAR(32) NOT NULL")
   // private String salt;
    private String restaurantRole;
    @ManyToOne
    private Restaurant restaurant;

    public RestaurantEmployee() {
    }

    public RestaurantEmployee(String firstName, String lastName, char gender, String email, String password, String restaurantRole, Restaurant restaurant) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.restaurantRole = restaurantRole;
        this.restaurant = restaurant;
    }
    
    

   /* public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }*/

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RestaurantEmployee)) {
            return false;
        }
        RestaurantEmployee other = (RestaurantEmployee) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RestaurantEmployee[ id=" + email + " ]";
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }




    /**
     * @return the restaurant
     */
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * @param restaurant the restaurant to set
     */
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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

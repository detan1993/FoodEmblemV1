/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author David
 */
@Entity
public class Restaurant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String contactNo;
    private String address;
    private String postalCode;
    private String email;
    private String apiKey;
    @OneToMany
    private List<Sensor> sensors ;
    
    @OneToMany
    private List<Dish> dishes;
    
    @OneToMany
    private List<Promotion> promotions;
    
    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantEmployee> restEmployee;

    public Restaurant() {
    }

    public Restaurant(String name, String contactNo, String address, String postalCode, String email, String apiKey, List<Sensor> sensors, List<Dish> dishes, List<Promotion> promotions, List<RestaurantEmployee> restEmployee) {
        this.name = name;
        this.contactNo = contactNo;
        this.address = address;
        this.postalCode = postalCode;
        this.email = email;
        this.apiKey = apiKey;
        this.sensors = sensors;
        this.dishes = dishes;
        this.promotions = promotions;
        this.restEmployee = restEmployee;
    }

    public Restaurant(String name, String contactNo, String address,  String postalCode, String email, String apiKey, List<Sensor> sensors , List<Dish> dishes) {
        this.name = name;
        this.contactNo = contactNo;
        this.address = address;
        this.postalCode = postalCode;
        this.email = email;
        this.apiKey = apiKey;
        this.sensors = sensors;
        this.dishes = dishes;
  
    }
    
    

    
    public Long getId() {
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
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurant)) {
            return false;
        }
        Restaurant other = (Restaurant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Restaurant[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the contactNo
     */
    public String getContactNo() {
        return contactNo;
    }

    /**
     * @param contactNo the contactNo to set
     */
    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postalCode to set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
     * @return the apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @param apiKey the apiKey to set
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @return the sensors
     */
    public List<Sensor> getSensors() {
        return sensors;
    }

    /**
     * @param sensors the sensors to set
     */
    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    /**
     * @return the dishes
     */
    public List<Dish> getDishes() {
        return dishes;
    }

    /**
     * @param dishes the dishes to set
     */
    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    /**
     * @return the promotions
     */
    public List<Promotion> getPromotions() {
        return promotions;
    }

    /**
     * @param promotions the promotions to set
     */
    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    /**
     * @return the restEmployee
     */
    public List<RestaurantEmployee> getRestEmployee() {
        return restEmployee;
    }

    /**
     * @param restEmployee the restEmployee to set
     */
    public void setRestEmployee(List<RestaurantEmployee> restEmployee) {
        this.restEmployee = restEmployee;
    }
    
}

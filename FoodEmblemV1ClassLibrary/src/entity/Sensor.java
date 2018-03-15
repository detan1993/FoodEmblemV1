/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author David
 */
@Entity
public class Sensor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sensorType;
    private double sensorRange;
    @OneToOne
    private Container container;
    @OneToOne
    private Fridge fridge;
    
    @OneToOne
    private RestaurantSeating restaurantSeating;

    public Sensor() {
    }

    public Sensor(String sensorType, double sensorRange, RestaurantSeating restaurantSeating) {
        this.sensorType = sensorType;
        this.sensorRange = sensorRange;
        this.restaurantSeating = restaurantSeating;
    }
    
    
    
    public Sensor(String sensorType, double sensorRange,  Fridge fridge) {
        this.sensorType = sensorType;
        this.sensorRange = sensorRange;
           this.fridge = fridge;
    }

    public Sensor(String sensorType, double sensorRange, Container container) {
        this.sensorType = sensorType;
        this.sensorRange = sensorRange;
        this.container = container;
    }
    
    
    
    
    

    public Sensor(String sensorType, double sensorRange, Container container, Fridge fridge, RestaurantSeating restaurantSeating) {
        this.sensorType = sensorType;
        this.sensorRange = sensorRange;
        this.container = container;
        this.fridge = fridge;
        this.restaurantSeating = restaurantSeating;
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
        if (!(object instanceof Sensor)) {
            return false;
        }
        Sensor other = (Sensor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Sensor[ id=" + id + " ]";
    }

    /**
     * @return the sensorType
     */
    public String getSensorType() {
        return sensorType;
    }

    /**
     * @param sensorType the sensorType to set
     */
    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

  
    /**
     * @return the container
     */
    public Container getContainer() {
        return container;
    }

    /**
     * @param container the container to set
     */
    public void setContainer(Container container) {
        this.container = container;
    }

    /**
     * @return the fridge
     */
    public Fridge getFridge() {
        return fridge;
    }

    /**
     * @param fridge the fridge to set
     */
    public void setFridge(Fridge fridge) {
        this.fridge = fridge;
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

    /**
     * @return the sensorRange
     */
    public double getSensorRange() {
        return sensorRange;
    }

    /**
     * @param sensorRange the sensorRange to set
     */
    public void setSensorRange(double sensorRange) {
        this.sensorRange = sensorRange;
    }

    /**
     * @return the restSeating
     */

    
}

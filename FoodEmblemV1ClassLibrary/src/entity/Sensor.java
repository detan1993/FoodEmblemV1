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
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sensorId;
    private String sensorType;
    private double sensorRange;
    @OneToOne
    private Container container;
    @OneToOne
    private Fridge fridge;
    /*private int major;
    private int minor;*/
    @OneToOne
    private RestaurantSeating restaurantSeating;

    private String sensorDescription;

    public Sensor() {

    }

    /*JY CODE
    public Sensor(String sensorId, String sensorType, double sensorRange, RestaurantSeating restaurantSeating, int major, int minor) {
       this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.sensorRange = sensorRange;
        this.restaurantSeating = restaurantSeating;
        this.major = major;
        this.minor = minor;
    }*/
    public Sensor(String sensorId, String sensorType, double sensorRange, RestaurantSeating restaurantSeating, String sensorDescription) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.sensorRange = sensorRange;
        this.restaurantSeating = restaurantSeating;
        this.sensorDescription = sensorDescription;
    }

    //David
    public Sensor(String sensorId, String sensorType, double sensorRange, Fridge fridge, String sensorDescription) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.sensorRange = sensorRange;
        this.fridge = fridge;

    }

    public Sensor(String sensorId, String sensorType, double sensorRange, Container container, String sensorDescription) {
        this.sensorId = sensorId;
        this.sensorType = sensorType;
        this.sensorRange = sensorRange;
        this.container = container;
        this.sensorDescription = sensorDescription;

    }

    /*JY ode
    public Sensor(String sensorType, double sensorRange, int major, int minor){
        this.sensorType = sensorType;
        this.sensorRange = sensorRange;
        this.major = major;
        this.minor = minor;
    }*/
 /*JY code
    public Sensor(String sensorType, double sensorRange,  Fridge fridge, int major, int minor) {
        this.sensorType = sensorType;
        this.sensorRange = sensorRange;
        this.fridge = fridge;
        this.major = major;
        this.minor = minor;
    }

    //JY ONE
    public Sensor(String sensorType, double sensorRange, Container container,int major, int minor) {
        this.sensorType = sensorType;
        this.sensorRange = sensorRange;
        this.container = container;
        this.major = major;
        this.minor = minor;
    }*/
 /*JY CODE
    public Sensor(String sensorType, double sensorRange, Container container, Fridge fridge, RestaurantSeating restaurantSeating, int major, int minor) {
        this.sensorType = sensorType;
        this.sensorRange = sensorRange;
        this.container = container;
        this.fridge = fridge;
        this.restaurantSeating = restaurantSeating;
        this.major = major;
        this.minor = minor;
    }*/
 /*   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

 /* @Override
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
    }*/
    @Override
    public String toString() {
        return "entity.Sensor[ id=" + getSensorId() + " ]";
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

//    /**
//     * @return the major
//     */
//    public int getMajor() {
//        return major;
//    }
//
//    /**
//     * @param major the major to set
//     */
//    public void setMajor(int major) {
//        this.major = major;
//    }
//
//    /**
//     * @return the minor
//     */
//    public int getMinor() {
//        return minor;
//    }
//
//    /**
//     * @param minor the minor to set
//     */
//    public void setMinor(int minor) {
//        this.minor = minor;
//    }
    /**
     * @return the sensorId
     */
    public String getSensorId() {
        return sensorId;
    }

    /**
     * @param sensorId the sensorId to set
     */
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    /**
     * @return the sensorDescription
     */
    public String getSensorDescription() {
        return sensorDescription;
    }

    /**
     * @param sensorDescription the sensorDescription to set
     */
    public void setSensorDescription(String sensorDescription) {
        this.sensorDescription = sensorDescription;
    }

    /**
     * @return the restSeating
     */
}

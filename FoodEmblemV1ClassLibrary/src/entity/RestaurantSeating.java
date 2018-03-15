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
import javax.persistence.OneToOne;

/**
 *
 * @author David
 */
@Entity
public class RestaurantSeating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String tableNo;
    private int seatCapacity;
    @OneToOne(mappedBy = "restaurantSeating")
    private Sensor sensor;
    
    
    @OneToMany(mappedBy="restSeating")
    private List<Reservation> reservations;

    public RestaurantSeating() {
    }

    public RestaurantSeating(String tableNo, int seatCapacity, Sensor sensor, List<Reservation> reservations) {
        this.tableNo = tableNo;
        this.seatCapacity = seatCapacity;
        this.sensor = sensor;
        this.reservations = reservations;
    }
    
    public RestaurantSeating(String tableNo, int seatCapacity) {
        this.tableNo = tableNo;
        this.seatCapacity = seatCapacity;
        this.sensor = sensor;

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
        if (!(object instanceof RestaurantSeating)) {
            return false;
        }
        RestaurantSeating other = (RestaurantSeating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RestaurantSeating[ id=" + id + " ]";
    }

    /**
     * @return the tableNo
     */
    public String getTableNo() {
        return tableNo;
    }

    /**
     * @param tableNo the tableNo to set
     */
    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    /**
     * @return the seatCapacity
     */
    public int getSeatCapacity() {
        return seatCapacity;
    }

    /**
     * @param seatCapacity the seatCapacity to set
     */
    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    /**
     * @return the sensor
     */
    public Sensor getSensor() {
        return sensor;
    }

    /**
     * @param sensor the sensor to set
     */
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    /**
     * @return the reservations
     */
    public List<Reservation> getReservations() {
        return reservations;
    }

    /**
     * @param reservations the reservations to set
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
}

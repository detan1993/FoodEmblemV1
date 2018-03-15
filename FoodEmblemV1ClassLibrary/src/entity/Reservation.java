/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author David
 */
@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date reservationDate;
    
    private String status;
    private int pax;
    private int noOfPaxSeated;  //if more than zero customer have been seated.

    @OneToMany
    private List<RestaurantCustomerOrder> restCustOrders;
    
    @ManyToOne
    private RestaurantSeating restSeating;

    public Reservation() {
    }

    public Reservation(Date reservationDate, String status, int pax, int noOfPaxSeated, List<RestaurantCustomerOrder> restCustOrders, RestaurantSeating restSeating) {
        this.reservationDate = reservationDate;
        this.status = status;
        this.pax = pax;
        this.noOfPaxSeated = noOfPaxSeated;
        this.restCustOrders = restCustOrders;
        this.restSeating = restSeating;
    }

    public Reservation(Date reservationDate, String status, int pax, int noOfPaxSeated) {
        this.reservationDate = reservationDate;
        this.status = status;
        this.pax = pax;
        this.noOfPaxSeated = noOfPaxSeated;
  
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
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Reservation[ id=" + id + " ]";
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the pax
     */
    public int getPax() {
        return pax;
    }

    /**
     * @param pax the pax to set
     */
    public void setPax(int pax) {
        this.pax = pax;
    }

    /**
     * @return the noOfPaxSeated
     */
    public int getNoOfPaxSeated() {
        return noOfPaxSeated;
    }

    /**
     * @param noOfPaxSeated the noOfPaxSeated to set
     */
    public void setNoOfPaxSeated(int noOfPaxSeated) {
        this.noOfPaxSeated = noOfPaxSeated;
    }

    /**
     * @return the restSeating
     */
    public RestaurantSeating getRestSeating() {
        return restSeating;
    }

    /**
     * @param restSeating the restSeating to set
     */
    public void setRestSeating(RestaurantSeating restSeating) {
        this.restSeating = restSeating;
    }

    /**
     * @return the reservationDate
     */
    public Date getReservationDate() {
        return reservationDate;
    }

    /**
     * @param reservationDate the reservationDate to set
     */
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    /**
     * @return the restCustOrders
     */
    public List<RestaurantCustomerOrder> getRestCustOrders() {
        return restCustOrders;
    }

    /**
     * @param restCustOrders the restCustOrders to set
     */
    public void setRestCustOrders(List<RestaurantCustomerOrder> restCustOrders) {
        this.restCustOrders = restCustOrders;
    }
    
}

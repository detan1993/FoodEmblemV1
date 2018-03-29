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
public class RestaurantCustomerOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private double totalPrice;
    
    private boolean isCooked;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTime;
    
    @OneToMany(mappedBy="restCustOrder") 
    private List<OrderDish> orderDishes;
    
    @ManyToOne
    private Promotion promotion;

    public RestaurantCustomerOrder() {
        this.isCooked = false;
    }

    public RestaurantCustomerOrder(double totalPrice, Date orderTime, List<OrderDish> orderDishes, Promotion promotion) {
        this();
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
        this.orderDishes = orderDishes;
        this.promotion = promotion;
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
        if (!(object instanceof RestaurantCustomerOrder)) {
            return false;
        }
        RestaurantCustomerOrder other = (RestaurantCustomerOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RestaurantCustomerOrder[ id=" + id + " ]";
    }

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the orderTime
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * @param orderTime the orderTime to set
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * @return the orderDishes
     */
    public List<OrderDish> getOrderDishes() {
        return orderDishes;
    }

    /**
     * @param orderDishes the orderDishes to set
     */
    public void setOrderDishes(List<OrderDish> orderDishes) {
        this.orderDishes = orderDishes;
    }

    /**
     * @return the promotion
     */
    public Promotion getPromotion() {
        return promotion;
    }

    /**
     * @param promotion the promotion to set
     */
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    /**
     * @return the status
     */
    public boolean getisCooked() {
        return isCooked;
    }

    /**
     * @param status the status to set
     */
    public void setisCooked(boolean isCooked) {
        this.isCooked = isCooked;
    }
    
}

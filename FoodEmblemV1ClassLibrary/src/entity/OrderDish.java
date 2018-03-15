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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author David
 */
@Entity
public class OrderDish implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int qty;
    @ManyToOne
    private RestaurantCustomerOrder restCustOrder;
    
   
    @ManyToOne
    private Dish dish;

    public OrderDish() {
    }

    public OrderDish(int qty, RestaurantCustomerOrder restCustOrder, Dish dish) {
        this.qty = qty;
        this.restCustOrder = restCustOrder;
        this.dish = dish;
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
        if (!(object instanceof OrderDish)) {
            return false;
        }
        OrderDish other = (OrderDish) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OrderDish[ id=" + id + " ]";
    }

    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * @return the restCustOrder
     */
    public RestaurantCustomerOrder getRestCustOrder() {
        return restCustOrder;
    }

    /**
     * @param restCustOrder the restCustOrder to set
     */
    public void setRestCustOrder(RestaurantCustomerOrder restCustOrder) {
        this.restCustOrder = restCustOrder;
    }

    /**
     * @return the dishes
     */
    public Dish getDish() {
        return dish;
    }

    /**
     * @param dishes the dishes to set
     */
    public void setDish(Dish dish) {
        this.dish = dish;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Dish;
import entity.OrderDish;
import entity.RestaurantCustomerOrder;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author shanw
 */
@Local(OrderDishControllerLocal.class)
@Remote(OrderDishControllerRemote.class)
@Stateless
public class OrderDishController implements OrderDishControllerRemote, OrderDishControllerLocal {

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public OrderDish createOrderDish(OrderDish od){
        em.persist(od);
        em.flush();
        em.refresh(od);
        return od;
    }
    
}

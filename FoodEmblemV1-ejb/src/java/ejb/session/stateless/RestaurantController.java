/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Restaurant;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author David
 */
@Local(RestaurantControllerLocal.class)
@Remote(RestaurantControllerRemote.class)
@Stateless
public class RestaurantController implements RestaurantControllerRemote, RestaurantControllerLocal {

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Restaurant createRestaurant(Restaurant newRestaurant){
        em.persist(newRestaurant);
        em.flush();
        em.refresh(newRestaurant);
        return newRestaurant;
    }
    
    @Override
    public List<Restaurant> retrieveRestaurant(){
        
      System.out.println("Calling");
      Query query = em.createQuery("SELECT r FROM Restaurant r");
      return query.getResultList();
        
    }
    
    @Override
    public Restaurant retrieveRestaurantById(int restid){
        Query q = em.createQuery("SELECT r FROM Restaurant r WHERE r.id = :restid");
        q.setParameter("restid", restid);
        return (Restaurant)q.getSingleResult();
    }
    
    
    
}
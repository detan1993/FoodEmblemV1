/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.OrderDish;
import entity.Restaurant;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    
     @Override
     public List<OrderDish>retrieveCustomerOrders(int restaurantId){
         Query q = em.createQuery("SELECT od FROM Restaurant r JOIN r.sensors s JOIN s.restaurantSeating rs JOIN rs.reservations rer JOIN rer.restCustOrders co JOIN co.orderDishes od  WHERE r.id = :restid AND rer.status = :status AND co.isCooked = :orderstatus");
         q.setParameter("restid", restaurantId);
         q.setParameter("status", "Active");
         q.setParameter("orderstatus", false);
         List<OrderDish>orderdishes = q.getResultList();
         return orderdishes;
     }
    
    @Override
    public List<Restaurant> retrievePartnerRestaurant(List<String> restApiKeys)
    {
        Query query;
        List<Restaurant> partnerRestaurants  = new ArrayList<>();
        for(String restKey : restApiKeys)
        {
            
            query = em.createQuery("SELECT r FROM Restaurant r WHERE r.apiKey =:restApiKey");
            query.setParameter("restApiKey", restKey);
            
            try{
               
                Restaurant information = (Restaurant)query.getSingleResult();
                System.out.println("************ REST INFO: " + information.getName());
                partnerRestaurants.add(information);
                
            }
            catch(NoResultException  Ex)
            {
                System.err.println("No such record");                  
            }
        }
        
        return partnerRestaurants;
        
    }
    
    
    
}
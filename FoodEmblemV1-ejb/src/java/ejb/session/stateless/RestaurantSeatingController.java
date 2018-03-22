/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Restaurant;
import entity.RestaurantSeating;
import java.util.ArrayList;
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
@Local(RestaurantSeatingControllerLocal.class)
@Remote(RestaurantSeatingControllerRemote.class)
@Stateless
public class RestaurantSeatingController implements RestaurantSeatingControllerRemote, RestaurantSeatingControllerLocal {

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public RestaurantSeating createNewSeating(RestaurantSeating newSeat)
    {
        em.persist(newSeat);
        em.flush();
        em.refresh(newSeat);
        return newSeat;
    }
    
    @Override
    public RestaurantSeating retrieveSeatById(long tableId)
    {
       System.out.println("getting restaurant seat ID" +  tableId);
       return em.find(RestaurantSeating.class, tableId);
        
    }
    
    
    @Override
    public List<RestaurantSeating> retrieveSeatsByRestaurantId(long restaurantId)
    {
        Query query = em.createQuery("SELECT r FROM Restaurant r WHERE r.id =:restaurantId");
        query.setParameter("restaurantId", restaurantId);
        
        List<RestaurantSeating> seat = new ArrayList<>();
       
        try{
            
             Restaurant result = (Restaurant) query.getSingleResult();
             for(int i = 0; i< result.getSensors().size(); i++)
             {
                 System.out.println("Sensor result is" + result.getSensors().get(i).getRestaurantSeating());
                 if(result.getSensors().get(i).getRestaurantSeating() !=  null)
                 {
                     seat.add(result.getSensors().get(i).getRestaurantSeating());
                 }
             }
        
            return seat;
                    
        }catch(Exception ex)
        {
            System.err.println("Error" + ex.getMessage());
            return null;
        }
        
        
    }
        
    
}
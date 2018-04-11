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
    public RestaurantSeating retrieveAllocatedSeat(int restid, int pax){
        //Query q = em.createQuery("SELECT rs FROM Restaurant r, RestaurantSeating rs WHERE r.id = :restid AND :pax <= rs.seatCapacity AND (rs.id NOT IN (SELECT rer.restSeating.id FROM Reservation rer))");
        Query q = em.createQuery("SELECT rs FROM Restaurant r JOIN r.sensors s JOIN s.restaurantSeating rs WHERE r.id = :restid AND :pax <= rs.seatCapacity AND (rs NOT IN (SELECT rer.restSeating FROM Reservation rer) OR rs IN (SELECT rer.restSeating FROM Reservation rer WHERE rer.status = 'Inactive'))");
        q.setParameter("restid", restid);
        q.setParameter("pax", pax); 
        List listofavailableseatings = q.getResultList();
        if (listofavailableseatings.size() == 0){
            return null;
        }
        return (RestaurantSeating)listofavailableseatings.get(0);
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
    
    @Override
    public List<Integer> retrieveActiveSeatsByRestaurantId(long restaurantId)
    {
        Query query = em.createQuery("SELECT rer.noOfPaxSeated FROM Restaurant r JOIN r.sensors s JOIN s.restaurantSeating rs JOIN rs.reservations rer WHERE r.id =:restaurantId AND rer.noOfPaxSeated>0 AND rer.status='Active'");
        query.setParameter("restaurantId", restaurantId);
        return query.getResultList();
    }
    
    @Override
    public long retrieveCountAllSeating(long restaurantId)
    {
        Query query = em.createQuery("SELECT count(rs.id) FROM Restaurant r JOIN r.sensors s JOIN s.restaurantSeating rs WHERE r.id =:restaurantId");
        query.setParameter("restaurantId", restaurantId);
        return (long)query.getSingleResult();
    }
}

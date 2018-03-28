/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Customer;
import entity.Reservation;
import entity.Restaurant;
import entity.RestaurantSeating;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
@Local(ReservationControllerLocal.class)
@Remote(ReservationControllerRemote.class)
@Stateless
public class ReservationController implements ReservationControllerRemote, ReservationControllerLocal {

    @EJB(name = "CustomerEntityControllerLocal")
    private CustomerEntityControllerLocal customerEntityControllerLocal;

    @EJB(name = "RestaurantSeatingControllerLocal")
    private RestaurantSeatingControllerLocal restaurantSeatingControllerLocal;
    
    

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    
    
    
    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public Reservation createReservation(Reservation newReservation , String customerId , Long seatId)
    {
        
        System.out.println("CREATE RESERVATION AT SESSION BEAN");
        RestaurantSeating seat = restaurantSeatingControllerLocal.retrieveSeatById(seatId);
        newReservation.setRestSeating(seat);  //tagging the servation to restaurant table
        em.persist(newReservation);
        em.flush();
        em.refresh(newReservation);
        
        //adding new rservation record to customer
        customerEntityControllerLocal.addNewReservationRecord(newReservation, customerId);
        
        return newReservation;
    }
    
    @Override
     public List<Reservation>getCustomerReservations(String customerEmail, String status){
         List<Reservation>reservationList = new ArrayList();
         Query query = em.createQuery("SELECT c FROM Customer c WHERE c.email =:email");
         query.setParameter("email", customerEmail);
          try{
            
             Customer c =  (Customer) query.getSingleResult();
             for(int i = 0; i< c.getReservations().size(); i++)
             {                     
               if (c.getReservations().get(i).getStatus().equals(status)){
               reservationList.add(c.getReservations().get(i));           
               }
                        
             }
             return reservationList;
        }catch(Exception ex)
        {
            System.err.println("Error" + ex.getMessage());
            return null;

        }
     }
     
     @Override
      public List <String> getRestaurantNameFromReservation(List<Reservation>reservationList){
           List<String>restNames = new ArrayList();
           try {
           for(int i = 0; i< reservationList.size(); i++)
             {             
                long seatingid = reservationList.get(i).getRestSeating().getId();
                System.out.print("Seat ID is " + seatingid);
                Query query = em.createQuery("SELECT r FROM Restaurant r JOIN r.sensors s JOIN s.restaurantSeating se WHERE se.id  =:seatid");
                query.setParameter("seatid", seatingid);
                Restaurant r = (Restaurant) query.getSingleResult();
                String restaurantname = r.getName();     
                restNames.add(restaurantname);
             }
           }
           catch (Exception ex){
               System.err.println("Error" + ex.getMessage());
            return null;
           }
          return restNames;
      }
}

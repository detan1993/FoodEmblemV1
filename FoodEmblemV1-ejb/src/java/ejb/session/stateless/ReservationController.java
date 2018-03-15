/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Reservation;
import entity.RestaurantSeating;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
    
}

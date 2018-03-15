/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Customer;
import entity.Reservation;
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

@Stateless
@Local(CustomerEntityControllerLocal.class)
@Remote(CustomerEntityControllerRemote.class)
public class CustomerEntityController implements CustomerEntityControllerRemote, CustomerEntityControllerLocal {

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public Customer createNewCustomer(Customer newCustomer){
        
        em.persist(newCustomer);
        em.flush();
        em.refresh(newCustomer);
        
        return newCustomer;
    }
    
    @Override
    public List<Customer> retrieveCustomer(){
        
       System.out.println("Calling");
      Query query = em.createQuery("SELECT e FROM Customer e");
      System.out.println("retrieveCustomer() is" + query.getResultList());
      return query.getResultList();
        
    }
    
    @Override
    public Customer login(String email, String password)
    {
        
        Customer customer = em.find(Customer.class, email);
        
        if(customer != null)
        {
            if(customer.getPassword().equals(password))
                return customer;
        }
        
        return null;
    }
    
    @Override
    public boolean addNewReservationRecord(Reservation newReservation , String customerId){

        
        System.out.println("ADDING NEW RESERVATION RECROD");
        try
        {
         Customer customer = em.find(Customer.class, customerId);
         customer.getReservations().add(newReservation);
         
         em.flush();
         em.refresh(customer);
         
         return true;
            
        }catch(Exception ex)
        {
            System.err.println(ex.getMessage());
        }
     
        return false;
        
        
    }
    
    
}

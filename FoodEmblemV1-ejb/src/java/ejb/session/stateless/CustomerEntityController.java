/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Customer;
import entity.Dish;
import entity.OrderDish;
import entity.Promotion;
import entity.Reservation;
import entity.RestaurantCustomerOrder;
import entity.Sensor;
import java.util.ArrayList;
import java.util.Date;
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
    
    @Override
     public Sensor retrieveReservationSeating(String email){
         Query q = em.createQuery("SELECT s FROM Customer c JOIN c.reservations r JOIN r.restSeating rs JOIN rs.sensor s WHERE c.email = :email AND r.status = :status");
         q.setParameter("status", "Active");
         q.setParameter("email", email);
          Sensor s;
         if (q.getResultList().size() != 0){
           s = (Sensor)q.getResultList().get(0);   
         }
         else {
             s = null;
         }
         return s;
     }
    
    @Override
    public RestaurantCustomerOrder addCustomerOrder(String email, List<OrderDish>orderDishes, Double total){
         System.out.println("ADDING Customer Order");
          RestaurantCustomerOrder restcustorder = new RestaurantCustomerOrder();
         try {
             Query q = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email");
             q.setParameter("email", email);
             Customer c = (Customer)q.getSingleResult();
             Date date = new Date();
             restcustorder.setOrderTime(date);
             List<OrderDish>orderlist = new ArrayList<OrderDish>();
             restcustorder.setTotalPrice(total);
             em.persist(restcustorder);
             long dishkey = 0;
             for (int i = 0; i < orderDishes.size(); i ++){
                OrderDish orderdish = new OrderDish();
                OrderDish jsondish = orderDishes.get(i);
                int quantity = jsondish.getQty();
                long dishid = jsondish.getId();
                q = em.createQuery("SELECT d FROM Dish d WHERE d.id = :dishid");
                q.setParameter("dishid", dishid); 
                Dish dish = (Dish)q.getSingleResult();
                dishkey = dish.getId();
                orderdish.setDishId(dishkey);
                //orderdish.setRestCustOrder(restcustorder);
               // orderdish.setDish(dish);
                orderdish.setQty(quantity);  
                em.persist(orderdish);
                restcustorder.getOrderDishes().add(orderdish);
                em.flush();
                orderlist.add(orderdish);
             }
             q = em.createQuery("SELECT p FROM Promotion p JOIN p.restaurant r JOIN r.dishes d WHERE d.id = :dishkey");
             q.setParameter("dishkey", dishkey);
             if (q.getResultList().size() > 0){
                 Promotion promotion = (Promotion) q.getResultList().get(0);
                 restcustorder.setPromotion(promotion);
             }
             q = em.createQuery("SELECT r FROM Customer c JOIN c.reservations r WHERE c.email = :email");
             q.setParameter("email", email);
             Reservation reservation = (Reservation)q.getSingleResult();
             reservation.getRestCustOrders().add(restcustorder);
             em.flush();
             em.refresh(restcustorder);
             return restcustorder;
         }
         catch (Exception ex){ 
             ex.printStackTrace();  
         }
         return restcustorder;
    }
    
    @Override
    public RestaurantCustomerOrder addCustomerOrder(RestaurantCustomerOrder order){
        em.persist(order);
        em.flush();
        em.refresh(order);
        
        return order;
    }
    
    @Override
    public RestaurantCustomerOrder retrieveSingleCustomerOrder(long customerOrderId) {
        Query query = em.createQuery("SELECT r FROM RestaurantCustomerOrder r WHERE r.id=:customerOrderId");
        query.setParameter("customerOrderId", customerOrderId);
        return (RestaurantCustomerOrder) query.getResultList().get(0);
    }
    
    @Override
    public void updateCustomerOrderCooked (long customerOrderId){
        RestaurantCustomerOrder order = retrieveSingleCustomerOrder(customerOrderId);
        System.out.println(order.getisCooked());
        order.setisCooked(true);
        em.flush();
        System.out.println(order.getisCooked());
    }
}

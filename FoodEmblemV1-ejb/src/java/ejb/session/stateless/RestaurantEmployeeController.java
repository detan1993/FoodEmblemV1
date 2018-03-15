/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Customer;
import entity.RestaurantEmployee;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author David
 */

@Local(RestaurantEmployeeControllerLocal.class)
@Remote(RestaurantEmployeeControllerRemote.class)
@Stateless
public class RestaurantEmployeeController implements RestaurantEmployeeControllerRemote, RestaurantEmployeeControllerLocal {

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public RestaurantEmployee createEmployee(RestaurantEmployee newEmployee){
        em.persist(newEmployee);
        em.flush();
        em.refresh(newEmployee);
        
        return newEmployee;
    }
    
    @Override
    public RestaurantEmployee login(String email, String password)
    {
        
        RestaurantEmployee employee = em.find(RestaurantEmployee.class, email);
        
        if(employee != null)
        {
            if(employee.getPassword().equals(password))
                return employee;
        }
        
        return null;
    }
    
    //this can only be triggered when the employee is login. 
    @Override
    public RestaurantEmployee getProfile(String email)
    {
         return em.find(RestaurantEmployee.class, email);
        
    }
    
}

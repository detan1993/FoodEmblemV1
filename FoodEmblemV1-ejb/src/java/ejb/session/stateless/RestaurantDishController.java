/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Dish;
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
@Local(RestaurantDishControllerLocal.class)
@Remote(RestaurantDishControllerRemote.class)
@Stateless
public class RestaurantDishController implements RestaurantDishControllerRemote, RestaurantDishControllerLocal {

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public Dish createDish(Dish newDish)
    {
        em.persist(newDish);
        em.flush();
        em.refresh(newDish);
        return newDish;
    }
    
    @Override
    public List<Dish> getDishes(Long restaurantId)
    {
        System.out.println("Get Dishes From Restaruant Id= " +  restaurantId);
        Query query = em.createQuery("SELECT r FROM Restaurant r where r.id=:restaurantId");
        query.setParameter("restaurantId", restaurantId);
        
        try{
            
            Restaurant rest = (Restaurant)query.getSingleResult();
            return rest.getDishes();
                    
        }catch(Exception ex)
        {
            System.err.println("Error" + ex.getMessage());
            return null;
        }
        
    }
    
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    
    
    
}

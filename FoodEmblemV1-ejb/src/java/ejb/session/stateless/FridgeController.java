/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Fridge;
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
@Local(FridgeControllerLocal.class)
@Remote(FridgeControllerRemote.class)
@Stateless
public class FridgeController implements FridgeControllerRemote, FridgeControllerLocal {

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public Fridge createFridge(Fridge newFridge)
    {
        
        em.persist(newFridge);
        em.flush();
        em.refresh(newFridge);
        return newFridge;
        
    }
    
    @Override
    public void updateTempTest(long restaurantId){
        
         Query query = em.createQuery("SELECT r FROM Restaurant r WHERE r.id =:restaurantId");
        query.setParameter("restaurantId", restaurantId);
        
 
        

        try{
            
             Restaurant result = (Restaurant) query.getSingleResult();
             for(int i = 0; i< result.getSensors().size(); i++)
             {
                 System.out.println("Sensor result is" + result.getSensors().get(i).getFridge());
                 if(result.getSensors().get(i).getFridge() !=  null)
                 {
                     Fridge f = result.getSensors().get(i).getFridge();
                     f.setTemperature(20.0);
                     em.flush();
                     em.refresh(f);
                     System.out.println("Temp is " + f.getTemperature());
                 }
             }
        }catch(Exception ex)
        {
            System.err.println("Error" + ex.getMessage());

        }
    }
    
    @Override
     public List<Fridge> retrieveFridgesByRestaurantId(long restaurantId)
    {
       
        Query query = em.createQuery("SELECT r FROM Restaurant r WHERE r.id =:restaurantId");
        query.setParameter("restaurantId", restaurantId);
        
        List<Fridge> fridges = new ArrayList<>();
        

        try{
            
             Restaurant result = (Restaurant) query.getSingleResult();
             for(int i = 0; i< result.getSensors().size(); i++)
             {
                 System.out.println("Sensor result is" + result.getSensors().get(i).getFridge());
                 if(result.getSensors().get(i).getFridge() !=  null)
                 {
                     fridges.add(result.getSensors().get(i).getFridge());
                     System.out.println("Temp is " + result.getSensors().get(i).getFridge().getTemperature());
                 }
             }
        
            return fridges;
                    
        }catch(Exception ex)
        {
            System.err.println("Error" + ex.getMessage());
            return null;
        }
        
        
    }
        
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

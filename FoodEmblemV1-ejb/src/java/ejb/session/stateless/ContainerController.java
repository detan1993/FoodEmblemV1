/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Container;
import entity.Restaurant;
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

@Local(ContainerControllerLocal.class)
@Remote(ContainerControllerRemote.class)
@Stateless
public class ContainerController implements ContainerControllerRemote, ContainerControllerLocal {

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public Container createContainer(Container newContainer)
    {
        em.persist(newContainer);
        em.flush();
        em.refresh(newContainer);
        return newContainer;
    }
    
   @Override
    public List<Container> retrieveContainersByRestaurantId(long restaurantId)
    {
        Query query = em.createQuery("SELECT r FROM Restaurant r WHERE r.id =:restaurantId");
        query.setParameter("restaurantId", restaurantId);
        
        List<Container> containers = new ArrayList<>();
        

        try{
            
             Restaurant result = (Restaurant) query.getSingleResult();
             for(int i = 0; i< result.getSensors().size(); i++)
             {
                 System.out.println("Sensor result is" + result.getSensors().get(i).getContainer());
                 if(result.getSensors().get(i).getContainer() !=  null)
                 {
                     containers.add(result.getSensors().get(i).getContainer());
                 }
             }
        
            return containers;
                    
        }catch(Exception ex)
        {
            System.err.println("Error" + ex.getMessage());
            return null;
        }
        
        
    }
    
}

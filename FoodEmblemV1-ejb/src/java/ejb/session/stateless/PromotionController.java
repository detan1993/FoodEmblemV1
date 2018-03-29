/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Promotion;
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
@Local(PromotionControllerLocal.class)
@Remote(PromotionControllerRemote.class)
@Stateless
public class PromotionController implements PromotionControllerRemote, PromotionControllerLocal {

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @Override
    public Promotion createPromotion(Promotion newPromotion)
    {
        
        em.persist(newPromotion);
        em.flush();
        em.refresh(newPromotion);
        
        return newPromotion;
        
    }
    
    @Override
    public Promotion retrieveRestaurantPromoFromBeacon(int major, int minor){
        Query q = em.createQuery("SELECT p FROM Promotion p JOIN p.restaurant r JOIN r.sensors s WHERE s.major = :major AND s.minor = :minor");
        q.setParameter("major", major);
        q.setParameter("minor", minor);
        Promotion p = (Promotion)q.getSingleResult();
        return p;
    }
    
    @Override
    public List<Promotion> retrievePromotionsByRestaurantId(long restaurantId)
    {
        Query query = em.createQuery("SELECT p FROM Promotion p WHERE p.restaurant.id =:restaurantId");
        query.setParameter("restaurantId", restaurantId);
       
        try{
            
            System.out.println("Number of promo from restaurantId : " + restaurantId + " is " + query.getResultList().size());
            
            if(query.getResultList().isEmpty())
                return populateEmptyData();
            
            
            return query.getResultList();
                    
        }catch(Exception ex)
        {
            System.err.println("Error" + ex.getMessage());
            return null;
        }
        
        
    }
    
    public List<Promotion> populateEmptyData()
    {
        List<Promotion> emptyDatas = new ArrayList<>();
        Promotion emptyData = new Promotion();
        emptyData.setId(null);
        emptyData.setPromotionPercentage(0);
        emptyData.setRestaurant(null);
        emptyData.setStartDateTime(null);
        emptyData.setEndDateTime(null);
        emptyDatas.add(emptyData);
      
        return  emptyDatas;
    }
    
    
}

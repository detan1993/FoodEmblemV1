/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Promotion;
import entity.Restaurant;
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
@Local(PromotionControllerLocal.class)
@Remote(PromotionControllerRemote.class)
@Stateless
public class PromotionController implements PromotionControllerRemote, PromotionControllerLocal {

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;
    @EJB(name = "RestaurantControllerLocal")
    private RestaurantControllerLocal restaurantControllerLocal;

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
     public Promotion createRestaurantPromotion(Promotion newPromotion, long restaurantid){
         Promotion newpromo = createPromotion(newPromotion);
         Restaurant r = restaurantControllerLocal.retrieveRestaurantById(restaurantid);
         r.getPromotions().add(newpromo);
         newpromo.setRestaurant(r);
         return newpromo;
     }
    
    @Override
    public Promotion retrieveRestaurantPromoFromBeacon(String sensorId){
        Query q = em.createQuery("SELECT p FROM Promotion p JOIN p.restaurant r JOIN r.sensors s WHERE s.sensorId = :sensorId");
        q.setParameter("sensorId", sensorId);
        Promotion p = new Promotion();
        if (q.getResultList().size() > 0){
            p = (Promotion)q.getResultList().get(0);
        }
        else {
            p = null;
        }
        
        return p;
    }
    
    @Override
     public Boolean deletePromotion(long promotionId){
         boolean deletesuccessful = false;
         try {
         Promotion p = em.find(Promotion.class, promotionId);
         em.remove(p);
         deletesuccessful = true;
         }
         catch (Exception ex){
             ex.printStackTrace();
         }
         return deletesuccessful;
     }
     
     @Override
      public Promotion updatePromotion (Promotion oldpromo){
         Promotion promo = em.find(Promotion.class, oldpromo.getId());
         promo.setDescription(oldpromo.getDescription());
         promo.setStartDateTime(oldpromo.getStartDateTime());
         promo.setEndDateTime(oldpromo.getEndDateTime());
         promo.setPromotionPercentage(oldpromo.getPromotionPercentage());
         em.flush();
         em.refresh(promo);
         return promo;
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

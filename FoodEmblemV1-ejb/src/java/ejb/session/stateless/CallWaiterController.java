/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CallWaiter;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JiongYi
 */
@Stateless
@Local(CallWaiterControllerLocal.class)
public class CallWaiterController implements CallWaiterControllerLocal{
    
     @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;
     
     public void persist(Object object) {
        em.persist(object);
    }
     
     @Override
     public boolean requestWaiter(String table){
         CallWaiter cw = new CallWaiter(table, new Date(), false);
         em.persist(cw);
         return true;
     }
     
     @Override
      public List<CallWaiter>GetRestaurantWaiterRequests(){
          Query q = em.createQuery("SELECT cw FROM CallWaiter cw WHERE cw.hasAttended = :status");
          q.setParameter("status", false);
          List<CallWaiter>reqlist = q.getResultList();
          return reqlist;
      }
      
      @Override
      public boolean clearRequest(long id){
          boolean clearsuccess = false;
          CallWaiter cw = em.find(CallWaiter.class, id);
          cw.setHasAttended(true);
          em.merge(cw);
          return clearsuccess;
      }
}

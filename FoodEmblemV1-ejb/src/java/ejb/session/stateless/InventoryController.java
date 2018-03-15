/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Inventory;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author David
 */
@Local(InventoryControllerLocal.class)
@Remote(InventoryControllerRemote.class)
@Stateless
public class InventoryController implements InventoryControllerRemote, InventoryControllerLocal {

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    @Override
    public Inventory createInventory(Inventory newInventory)
    {
        em.persist(newInventory);
        em.flush();
        em.refresh(newInventory);
        
        return newInventory;
        
    }
    
    
}

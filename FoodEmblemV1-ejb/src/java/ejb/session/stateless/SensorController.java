/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Container;
import entity.Fridge;
import entity.Inventory;
import entity.Restaurant;
import entity.Sensor;
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
@Local(SensorControllerLocal.class)
@Remote(SensorControllerRemote.class)
@Stateless
public class SensorController implements SensorControllerRemote, SensorControllerLocal {

    @PersistenceContext(unitName = "FoodEmblemV1-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public Sensor createSensor(Sensor newSensor) {

        em.persist(newSensor);
        em.flush();
        em.refresh(newSensor);
        return newSensor;
    }

    @Override
    public List<Sensor> retrieveSensor(long restaurantId) {
        System.out.println("retrieve Sensor by restaurantId = " + restaurantId);
        Query query = em.createQuery("SELECT r FROM Restaurant r WHERE r.id = :restaurantId");
        query.setParameter("restaurantId", restaurantId);
        Restaurant rest = ((Restaurant) query.getSingleResult());

        try {
            System.out.println("Try Sensor= " + rest.getSensors().size());
            return rest.getSensors();
        } catch (Exception ex) {

            System.out.println("Exception " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public void updateFridgeTemp(Long restaurantId, Long fridgeId, double tempValue) {

        System.out.println("**************** inside update fridge Temp");
        List<Sensor> restSensors = retrieveSensor(restaurantId);
        for (Sensor s : restSensors) {
            Fridge f = s.getFridge();
            if (f != null) {
                System.out.println("FridgeId is " + f.getId());
                if (f.getId() == fridgeId) {
                    //s.getFridge().setTemperature(tempVl);
                    f.setTemperature(tempValue);
                    em.flush();
                    break;
                }
            }
        }

    }

    // container ID can uniquely identify the inventory inside the ID. 
    //1 container ID only have 1 inventory object, so we can pass the weight directly without having a need to pass Inventory Project.
    
    @Override
    public void updateContainerInventoryWeight(Long restaurantId, Long containerId, double newInventoryWeight){
        
      System.out.println("**************** inside update inventory weight");
        List<Sensor> restSensors = retrieveSensor(restaurantId);
        for (Sensor s : restSensors) {
            Container c = s.getContainer();
            if (c != null) {

                System.out.println("container ID is  " + c.getId());
                if (c.getId() == containerId) {

                    Inventory inventoryToUpdate = c.getInventory();
                    inventoryToUpdate.setWeight(newInventoryWeight);
                                    //s.getFridge().setTemperature(tempVl);
                    em.flush();
                    break;
                }
            }
        }
               
    }
    
   /* @Override
    public void updateContainerInventoryWeight(Long restaurantId, Long containerId, Inventory inventory) {

        System.out.println("**************** inside update inventory weight");
        List<Sensor> restSensors = retrieveSensor(restaurantId);
        for (Sensor s : restSensors) {
            Container c = s.getContainer();
            if (c != null) {

                System.out.println("container ID is  " + c.getId());
                if (c.getId() == containerId) {

                    Inventory inventoryToUpdate = c.getInventory();
                    if (inventoryToUpdate.getId() == inventory.getId()) {

                        inventoryToUpdate.setName(inventory.getName());
                        inventoryToUpdate.setThreshold(inventory.getThreshold());
                        inventoryToUpdate.setWeight(inventory.getWeight());
                    }

                    //s.getFridge().setTemperature(tempVl);
                    em.flush();
                    break;
                }
            }
        }

    }*/
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
      
    @Override
     public List<Sensor> retrieveFridgeSensor(long restaurantId) {
         
        try {
        System.out.println("retrieve Sensor by restaurantId = " + restaurantId);
        Query query = em.createQuery("SELECT r FROM Restaurant r WHERE r.id = :restaurantId");
       // Query querys = em.createQuery("SELECT r FROM Restaurant r WHERE r.sensors = :restaurantId");
    
        query.setParameter("restaurantId", restaurantId);
        Restaurant rest = ((Restaurant) query.getSingleResult());

        
        List<Sensor> fridgeSensor = new ArrayList<>();
        List<Sensor> allSensor = rest.getSensors();
        
        for(Sensor s : allSensor){
            if(s.getFridge() != null)
                fridgeSensor.add(s);
        }
        
      
            if(!fridgeSensor.isEmpty())
            System.out.println("Try Sensor= " + fridgeSensor.get(0).getSensorId());
            
            return fridgeSensor;
        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.println("Exception " + ex.getMessage());
            return null;
        }

    }
}

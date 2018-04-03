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

    @Override
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

    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

}

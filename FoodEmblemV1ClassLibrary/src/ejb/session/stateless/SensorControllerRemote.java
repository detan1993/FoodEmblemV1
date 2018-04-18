/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Inventory;
import entity.Sensor;
import java.util.List;


public interface SensorControllerRemote {
    public Sensor createSensor(Sensor newSensor);
    public List<Sensor> retrieveSensor(long restaurantId);

    public void updateFridgeTemp(Long restaurantId, Long fridgeId, double tempValue);

//    public void updateContainerInventoryWeight(Long restaurantId, Long containerId, Inventory inventory);

    public List<Sensor> retrieveFridgeSensor(long restaurantId);

    public void updateContainerInventoryWeight(Long restaurantId, Long containerId, double newInventoryWeight);
}

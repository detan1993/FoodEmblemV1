/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Sensor;
import java.util.List;


public interface SensorControllerLocal {

    public Sensor createSensor(Sensor newSensor);

    public List<Sensor> retrieveSensor(long restaurantId);
    
}

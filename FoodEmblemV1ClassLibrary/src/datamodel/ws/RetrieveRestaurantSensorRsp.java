/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.Sensor;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */

@XmlRootElement
@XmlType(name = "retrieveRestaurantSensorRsp", propOrder = {
    "sensors"
})

public class RetrieveRestaurantSensorRsp implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private List<Sensor> sensors;
    
    
    public RetrieveRestaurantSensorRsp() {
    }

    public RetrieveRestaurantSensorRsp(List<Sensor> sensors) {
        this.sensors = sensors;
    }
    
    /**
     * @return the sensors
     */
    public List<Sensor> getSensors() {
        return sensors;
    }

    /**
     * @param sensors the sensors to set
     */
    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
        
        
    
}

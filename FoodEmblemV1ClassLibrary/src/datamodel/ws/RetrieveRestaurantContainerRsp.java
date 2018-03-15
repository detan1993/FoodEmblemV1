/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.Container;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */


@XmlRootElement
@XmlType(name = "retrieveRestaurantContainerRsp", propOrder = {
    "containers"
})

public class RetrieveRestaurantContainerRsp implements Serializable {
    
     private static final long serialVersionUID = 1L;
     
     private List<Container> containers;

    public RetrieveRestaurantContainerRsp() {
    }

    public RetrieveRestaurantContainerRsp(List<Container> containers) {
        this.containers = containers;
    }
    
    

    /**
     * @return the containers
     */
    public List<Container> getContainers() {
        return containers;
    }

    /**
     * @param containers the containers to set
     */
    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }
     
     
     
     
     
    
}

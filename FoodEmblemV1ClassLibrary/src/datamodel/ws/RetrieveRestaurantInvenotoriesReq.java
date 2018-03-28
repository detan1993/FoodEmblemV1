/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */

@XmlRootElement
@XmlType(name = "retrieveRestaurantInvenotoriesReq", propOrder = {
   "listOfRestaurantKey"
})

public class RetrieveRestaurantInvenotoriesReq implements Serializable {
    
    
    private static final long serialVersionUID = 1L;
    
    private List<String> listOfRestaurantKey;

    public RetrieveRestaurantInvenotoriesReq() {
    }

    public RetrieveRestaurantInvenotoriesReq(List<String> listOfRestaurantKey) {
        this.listOfRestaurantKey = listOfRestaurantKey;
    }

    /**
     * @return the listOfRestaurantKey
     */
    public List<String> getListOfRestaurantKey() {
        return listOfRestaurantKey;
    }

    /**
     * @param listOfRestaurantKey the listOfRestaurantKey to set
     */
    public void setListOfRestaurantKey(List<String> listOfRestaurantKey) {
        this.listOfRestaurantKey = listOfRestaurantKey;
    }
    
    
    
    
    
}

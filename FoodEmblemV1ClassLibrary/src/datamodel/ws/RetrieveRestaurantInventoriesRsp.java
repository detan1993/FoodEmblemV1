/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.Restaurant;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */


@XmlRootElement
@XmlType(name = "retrieveRestaurantInvenotoriesRsp", propOrder = {
   "partnerRestaurant"
})
public class RetrieveRestaurantInventoriesRsp implements Serializable {
    
    
    private static final long serialVersionUID = 1L;
    
    
    private List<Restaurant> partnerRestaurant;

    public RetrieveRestaurantInventoriesRsp() {
    }

    public RetrieveRestaurantInventoriesRsp(List<Restaurant> partnerRestaurant) {
        this.partnerRestaurant = partnerRestaurant;
    }

    /**
     * @return the partnerRestaurant
     */
    public List<Restaurant> getPartnerRestaurant() {
        return partnerRestaurant;
    }

    /**
     * @param partnerRestaurant the partnerRestaurant to set
     */
    public void setPartnerRestaurant(List<Restaurant> partnerRestaurant) {
        this.partnerRestaurant = partnerRestaurant;
    }
    
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author JiongYi
 */
@XmlRootElement
@XmlType(name = "retrieveSupplierRestaurant", propOrder = { 
    "restDetails"
})
public class RetrieveSupplierRestaurant {
  private List<String>restDetails;
    
  public RetrieveSupplierRestaurant(){
      
  }
  
    public RetrieveSupplierRestaurant(List<String>restDetails){
        this.restDetails = restDetails;
    }

    /**
     * @return the restDetails
     */
    public List<String> getRestDetails() {
        return restDetails;
    }

    /**
     * @param restDetails the restDetails to set
     */
    public void setRestDetails(List<String> restDetails) {
        this.restDetails = restDetails;
    }
    
   
}

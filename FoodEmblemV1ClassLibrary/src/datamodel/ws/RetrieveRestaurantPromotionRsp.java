/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import entity.Promotion;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */

@XmlRootElement
@XmlType(name = "retrieveRestaurantPromotionRsp", propOrder = {
    "promotions" , "promotion"
})
public class RetrieveRestaurantPromotionRsp implements Serializable {
        
        private static final long serialVersionUID = 1L;
    
     private List<Promotion> promotions;
     private Promotion promotion;

    public RetrieveRestaurantPromotionRsp() {
    }

    public RetrieveRestaurantPromotionRsp(Promotion promotion){
        this.promotion = promotion;
    }
    
    public RetrieveRestaurantPromotionRsp(List<Promotion> promotions) {
        this.promotions = promotions;
    }
     
    /**
     * @return the promotions
     */
    public List<Promotion> getPromotions() {
        return promotions;
    }

    /**
     * @param promotions the promotions to set
     */
    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    /**
     * @return the promotion
     */
    public Promotion getPromotion() {
        return promotion;
    }

    /**
     * @param promotion the promotion to set
     */
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}

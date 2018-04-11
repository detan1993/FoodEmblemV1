/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author shanw
 */

@XmlRootElement
@XmlType(name = "retrieveRestaurantSeatingActiveRsp", propOrder = {
    "rsCount"
})

public class RetrieveCountAllSeatingRsp {
    private long rsCount;

    public RetrieveCountAllSeatingRsp() {
    }

    public RetrieveCountAllSeatingRsp(long rsCount) {
        this.rsCount = rsCount;
    }

    public long getRsCount() {
        return rsCount;
    }

    public void setRsCount(long rsCount) {
        this.rsCount = rsCount;
    }
    
    
}

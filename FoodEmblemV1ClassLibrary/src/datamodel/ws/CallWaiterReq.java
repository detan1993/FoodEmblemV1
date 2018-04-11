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
 * @author JiongYi
 */
@XmlRootElement
@XmlType(name = "callWaiterReq", propOrder = { 
    "tableid"
})
public class CallWaiterReq {
    private String tableid;
    
    public CallWaiterReq(){
        
    }
    
    public CallWaiterReq(String tableid){
        this.tableid = tableid;
    }

    /**
     * @return the tableid
     */
    public String getTableid() {
        return tableid;
    }

    /**
     * @param tableid the tableid to set
     */
    public void setTableid(String tableid) {
        this.tableid = tableid;
    }
    
}

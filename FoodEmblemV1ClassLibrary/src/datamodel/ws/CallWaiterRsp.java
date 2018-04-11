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
@XmlType(name = "callWaiterRsp", propOrder = { 
    "callSuccess"
})
public class CallWaiterRsp {
    private boolean callSuccess;

    public CallWaiterRsp(){
        
    }
    
    public CallWaiterRsp(boolean callSuccess){
        this.callSuccess = callSuccess;
    }
    /**
     * @return the callSuccess
     */
    public boolean isCallSuccess() {
        return callSuccess;
    }

    /**
     * @param callSuccess the callSuccess to set
     */
    public void setCallSuccess(boolean callSuccess) {
        this.callSuccess = callSuccess;
    }
    
}

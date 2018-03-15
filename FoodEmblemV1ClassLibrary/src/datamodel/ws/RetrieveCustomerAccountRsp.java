/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel.ws;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author David
 */


@XmlRootElement
@XmlType(name = "retrieveRestaurantSensorReq", propOrder = {
    "loginSuccess"
})

public class RetrieveCustomerAccountRsp implements Serializable {
    
     
    private static final long serialVersionUID = 1L;
    
    //private boolean status;
    private boolean loginSuccess;


    public RetrieveCustomerAccountRsp() {
    }

    public RetrieveCustomerAccountRsp(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }
    
    

    /**
     * @return the loginSuccess
     */
    public boolean getLoginSuccess() {
        return loginSuccess;
    }

    /**
     * @param loginSuccess the loginSuccess to set
     */
    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }
    
}

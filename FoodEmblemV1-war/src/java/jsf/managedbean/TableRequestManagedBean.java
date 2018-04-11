/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.session.stateless.CallWaiterControllerLocal;
import entity.CallWaiter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author JiongYi
 */
@Named(value = "tableRequestManagedBean")
@ViewScoped
public class TableRequestManagedBean implements Serializable{
@EJB
private CallWaiterControllerLocal callwaitercontroller;
    /**
     * Creates a new instance of TableRequestManagedBean
     */

    private List<CallWaiter>listofrequests;
    public TableRequestManagedBean() {
        
        listofrequests = new ArrayList<>();
    }
    
    
    
    @PostConstruct
    public void postConstruct()
    {
        setListofrequests(callwaitercontroller.GetRestaurantWaiterRequests());
    }
    
    public void clearRequest(long id){
        callwaitercontroller.clearRequest(id);
         setListofrequests(callwaitercontroller.GetRestaurantWaiterRequests());
    }

    /**
     * @return the listofrequests
     */
    public List<CallWaiter> getListofrequests() {
        return listofrequests;
    }

    /**
     * @param listofrequests the listofrequests to set
     */
    public void setListofrequests(List<CallWaiter> listofrequests) {
        this.listofrequests = listofrequests;
    }
    
}

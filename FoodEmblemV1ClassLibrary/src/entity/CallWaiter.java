/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JiongYi
 */
@Entity
public class CallWaiter {
     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String tableId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestTime;
    private boolean hasAttended;
    
    public CallWaiter(){
        
    }
    
    public CallWaiter(String tableId, Date requestTime, boolean hasAttended){
        this.tableId = tableId;
        this.requestTime = requestTime;
        this.hasAttended = hasAttended;
    }
     /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the tableId
     */
    public String getTableId() {
        return tableId;
    }

    /**
     * @param tableId the tableId to set
     */
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    /**
     * @return the requestTime
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * @param requestTime the requestTime to set
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * @return the hasAttended
     */
    public boolean isHasAttended() {
        return hasAttended;
    }

    /**
     * @param hasAttended the hasAttended to set
     */
    public void setHasAttended(boolean hasAttended) {
        this.hasAttended = hasAttended;
    }
}

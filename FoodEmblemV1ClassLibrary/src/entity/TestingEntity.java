/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author David
 */
@Entity
public class TestingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }
    
    private String testName;
    
    private String testDescription;
    
    @Temporal(TemporalType.DATE)
    private Date testDate;

    public TestingEntity() {
    }

    public TestingEntity(String testName, String testDescription, Date testDate) {
        this.testName = testName;
        this.testDescription = testDescription;
        this.testDate = testDate;
    }
    
    
    
    
    
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestingEntity)) {
            return false;
        }
        TestingEntity other = (TestingEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TestingEntity[ id=" + id + " ]";
    }

    /**
     * @return the testName
     */
    public String getTestName() {
        return testName;
    }

    /**
     * @param testName the testName to set
     */
    public void setTestName(String testName) {
        this.testName = testName;
    }

    /**
     * @return the testDescription
     */
    public String getTestDescription() {
        return testDescription;
    }

    /**
     * @param testDescription the testDescription to set
     */
    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    /**
     * @return the testDate
     */
    public Date getTestDate() {
        return testDate;
    }

    /**
     * @param testDate the testDate to set
     */
    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }
    
}

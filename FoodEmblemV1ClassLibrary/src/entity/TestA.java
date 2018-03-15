/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author David
 */
@Entity
public class TestA implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    @OneToOne
    private TestB testb;
    
    @OneToOne
    private TestC testc;

    public TestA() {
    }

    public TestA(String name, TestB testb, TestC testc) {
        this.name = name;
        this.testb = testb;
        this.testc = testc;
    }

    
    
    public Long getId() {
        return id;
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
        if (!(object instanceof TestA)) {
            return false;
        }
        TestA other = (TestA) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TestA[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the testb
     */
    public TestB getTestb() {
        return testb;
    }

    /**
     * @param testb the testb to set
     */
    public void setTestb(TestB testb) {
        this.testb = testb;
    }

    /**
     * @return the testc
     */
    public TestC getTestc() {
        return testc;
    }

    /**
     * @param testc the testc to set
     */
    public void setTestc(TestC testc) {
        this.testc = testc;
    }
    
}

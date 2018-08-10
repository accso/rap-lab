/*
 * Entity1.java
 *
 * Created on 10. September 2007, 14:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.h_da.library.component1.entity;

import de.h_da.library.component1.type.DataType1;
import de.h_da.library.component1.type.DataType2;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class Entity1
 *
 */
@Entity
public class Entity1 implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String attribute1;
    
    private DataType1 attribute2;
    
    
    /** Creates a new instance of Entity1 */
    public Entity1() {
    }
    
    /**
     * Gets the id of this Entity1.
     * @return the id
     */
    public Long getId() {
        return this.id;
    }
    
    /**
     * Sets the id of this Entity1 to the specified value.
     * @param id the new id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Returns a hash code value for the object.  This implementation computes
     * a hash code value based on the id fields in this object.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getId() != null ? this.getId().hashCode() : 0);
        return hash;
    }
    
    /**
     * Determines whether another object is equal to this Entity1.  The result is
     * <code>true</code> if and only if the argument is not null and is a Entity1 object that
     * has the same id field values as this object.
     * @param object the reference object with which to compare
     * @return <code>true</code> if this object is the same as the argument;
     * <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entity1)) {
            return false;
        }
        Entity1 other = (Entity1)object;
        if (this.getId() != other.getId() && (this.getId() == null || !this.getId().equals(other.getId()))) return false;
        return true;
    }
    
    /**
     * Returns a string representation of the object.  This implementation constructs
     * that representation based on the id fields.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Entity1[id=" + getId() + " attribute1=" + getAttribute1() + " attribute2=" + getAttribute2() + "]";
    }
    
    public String getAttribute1() {
        return attribute1;
    }
    
    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }
    
    

    public DataType1 getAttribute2() {
        return attribute2;
    }
 
    public void setAttribute2(DataType1 attribute2) {
        this.attribute2 = attribute2;
    }

}

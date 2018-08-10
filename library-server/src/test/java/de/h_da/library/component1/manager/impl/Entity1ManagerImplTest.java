/*
 * Entity1ManagerImplTest.java
 * JUnit based test
 *
 * Created on 10. September 2007, 15:54
 * Updated on 05. July 2012
 */
package de.h_da.library.component1.manager.impl;

import de.h_da.library.component1.type.DataType1;
import de.h_da.library.component1.entity.Entity1;
import de.h_da.library.component1.manager.Entity1Manager;
import de.h_da.library.configuration.LibraryTest;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.NamingException;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class Entity1ManagerImplTest extends LibraryTest {

    @EJB
    private Entity1Manager entity1Manager;

    /**
     * Test of create method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testCreate() {
        Entity1 entity1, entity1Created;

        // preparation
        entity1 = new Entity1();
        entity1.setAttribute1("att1");
        entity1.setAttribute2(DataType1.VALUE1);

        // execution
        entity1Created = entity1Manager.create(entity1);
        
        // evaluation
        assertNotNull(entity1Created);
        assertNotNull(entity1Created.getId());
        assertEquals("att1", entity1Created.getAttribute1());
        assertEquals(DataType1.VALUE1, entity1Created.getAttribute2());
    }

    /**
     * Test of edit method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testEdit() {
        Entity1 entity1New, entity1Created, entity1Found;

        // preparation
        entity1New = new Entity1();
        entity1New.setAttribute1("attOld");
        entity1New.setAttribute2(DataType1.VALUE1);
        entity1Created = entity1Manager.create(entity1New);
        entity1Created.setAttribute1("attNew");
        entity1Created.setAttribute2(DataType1.VALUE2);
        
        // execution
        entity1Manager.edit(entity1Created);
        
        // evaluation
        entity1Found = entity1Manager.findById(entity1Created.getId());
        assertEquals("attNew", entity1Found.getAttribute1());
        assertEquals(DataType1.VALUE2, entity1Found.getAttribute2());
    }

    /**
     * Test of destroy method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testDestroy() {
        Entity1 newEntity1Record, entity1Created, entity1Found;

        // preparation
        newEntity1Record = new Entity1();
        entity1Created = entity1Manager.create(newEntity1Record);
        
        // execution
        entity1Manager.destroy(entity1Created);

        // evaluation
        entity1Found = entity1Manager.findById(entity1Created.getId());
        assertNull(entity1Found);
    }

    /**
     * Test of findById method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testFindById() {
        Entity1 entity1New, entity1Created, entity1Found;

        // preparation
        entity1New = new Entity1();
        entity1New.setAttribute1("att1");
        entity1New.setAttribute2(DataType1.VALUE1);
        entity1Created = entity1Manager.create(entity1New);
        
        // execution
        entity1Found = entity1Manager.findById(entity1Created.getId());
        
        // evaluation
        assertNotNull(entity1Found);
        assertEquals(entity1Created.getId(), entity1Found.getId());
        assertEquals("att1", entity1Found.getAttribute1());
        assertEquals(DataType1.VALUE1, entity1Found.getAttribute2());
    }

    /**
     * Test of findAll method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testFindAll() {
        Entity1 entity1NewA, entity1NewB;
        List<Entity1> entities1FoundBefore, entities1FoundAfter;

        // preparation
        entities1FoundBefore = entity1Manager.findAll();
        entity1NewA = new Entity1();
        entity1NewB = new Entity1();
        entity1Manager.create(entity1NewA);
        entity1Manager.create(entity1NewB);
        
        // execution
        entities1FoundAfter = entity1Manager.findAll();
        
        // evaluation
        assertEquals(2, entities1FoundAfter.size() - entities1FoundBefore.size());
    }
}

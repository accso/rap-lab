/*
 * Entity2ManagerImplTest.java
 * JUnit based test
 *
 * Created on 10. September 2007, 18:25
 * Updated on 05. July 2012
 */

package de.h_da.library.component1.manager.impl;

import de.h_da.library.component1.entity.Entity1;
import de.h_da.library.component1.entity.Entity2;
import de.h_da.library.component1.manager.Entity1Manager;
import de.h_da.library.component1.manager.Entity2Manager;
import de.h_da.library.configuration.LibraryTest;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class Entity2ManagerImplTest extends LibraryTest {

    @EJB
    Entity1Manager entity1Manager;
    @EJB
    Entity2Manager entity2Manager;

    /**
     * Test of create method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testCreate() {
        Entity1 entity1, entity1Created;
        Entity2 entity2, entity2Created;

        // preparation        
        entity1 = new Entity1();
        entity1.setAttribute1("test1");
        entity1Created = entity1Manager.create(entity1);
        entity2 = new Entity2();

        // execution
        entity2Created = entity2Manager.create(entity2, entity1Created.getId());

        // evaluation
        assertNotNull(entity2Created);
        assertNotNull(entity2Created.getId());
        assertNotNull(entity2Created.getEntity1());
        assertEquals(entity1Created.getId(), entity2Created.getEntity1().getId());
    }

    /**
     * Test of edit method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testEdit() {
        Entity1 entity1a, entity1aCreated, entity1b, entity1bCreated;
        Entity2 entity2, entity2Created, entity2found;

        // preparation        
        entity1a = new Entity1();
        entity1aCreated = entity1Manager.create(entity1a);
        entity2 = new Entity2();
        entity2Created = entity2Manager.create(entity2, entity1aCreated.getId());
        entity1b = new Entity1();
        entity1bCreated = entity1Manager.create(entity1b);

        // execution
        entity2Manager.edit(entity2Created, entity1bCreated.getId());

        // evaluation
        entity2found = entity2Manager.findById(entity2Created.getId());
        assertNotNull(entity2found);
        assertNotNull(entity2found.getEntity1());
        assertEquals(entity1bCreated.getId(), entity2found.getEntity1().getId());
    }

    /**
     * Test of destroy method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testDestroy() {
        Entity1 entity1, entity1Created, entity1Found;
        Entity2 entity2, entity2Created, entity2Found;

        // preparation        
        entity1 = new Entity1();
        entity1Created = entity1Manager.create(entity1);
        entity2 = new Entity2();
        entity2Created = entity2Manager.create(entity2, entity1Created.getId());

        // execution
        entity2Manager.destroy(entity2Created);

        // evaluation
        entity2Found = entity2Manager.findById(entity2Created.getId());
        entity1Found = entity1Manager.findById(entity1Created.getId());
        assertNull(entity2Found);       // delete successful
        assertNotNull(entity1Found);    // no cacading delete
    }

    /**
     * Test of findById method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testFind() {
        Entity1 entity1, entity1Created;
        Entity2 entity2, entity2Created, entity2Found;

        // preparation        
        entity1 = new Entity1();
        entity1Created = entity1Manager.create(entity1);
        entity2 = new Entity2();
        entity2Created = entity2Manager.create(entity2, entity1Created.getId());

        // execution
        entity2Found = entity2Manager.findById(entity2Created.getId());

        // evaluation
        assertNotNull(entity2Found);
        assertEquals(entity2Created.getId(), entity2Found.getId());
        assertNotNull(entity2Found.getEntity1());
        assertEquals(entity1Created.getId(), entity2Found.getEntity1().getId());
    }

    /**
     * Test of findAll method, of class de.h_da.library.component1.manager.impl.Entity1ManagerImpl.
     */
    @Test
    public void testFindAll() {
        Entity1 entity1New, entity1Created;
        Entity2 entity2NewA, entity2NewB;
        List<Entity2> entities2FoundBefore, entities2FoundAfter;

        // preparation        
        entities2FoundBefore = entity2Manager.findAll();
        entity1New = new Entity1();
        entity2NewA = new Entity2();
        entity2NewB = new Entity2();
        entity1Created = entity1Manager.create(entity1New);
        entity2Manager.create(entity2NewA, entity1Created.getId());
        entity2Manager.create(entity2NewB, entity1Created.getId());

        // execution
        entities2FoundAfter = entity2Manager.findAll();

        // evaluation
        assertEquals(2, entities2FoundAfter.size() - entities2FoundBefore.size());
    }

}

/*
 * UseCase1ImplTest.java
 * JUnit based test
 *
 * Created on 10. September 2007, 18:26
 * Updated on 05. July 2012
 */

package de.h_da.library.component1.usecase.impl;

import de.h_da.library.LibraryRuntimeException;
import de.h_da.library.component1.entity.Entity1;
import de.h_da.library.component1.manager.Entity1Manager;
import de.h_da.library.component1.type.DataType1;
import de.h_da.library.component1.usecase.UseCase1Remote;
import de.h_da.library.configuration.LibraryTest;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.EJBs;
import javax.naming.NamingException;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class UseCase1ImplTest extends LibraryTest {

    @EJB
    Entity1Manager entity1Manager;
    @EJB
    UseCase1Remote useCase1;

    /**
     * Test of useCaseMethod1 method, of class de.h_da.library.component1.usecase.impl.UseCase1Impl.
     */
    @Test
    public void testUseCaseMethod1() {
        Entity1 entity1NewA, entity1NewB;
        List<Entity1> entities1FoundBefore, entities1FoundAfter;

        // preparation
        entities1FoundBefore = useCase1.useCaseMethod1();
        entity1NewA = new Entity1();
        entity1NewB = new Entity1();
        entity1Manager.create(entity1NewA);
        entity1Manager.create(entity1NewB);

        // execution
        entities1FoundAfter = useCase1.useCaseMethod1();

        // evaluation
        assertEquals(2, entities1FoundAfter.size() - entities1FoundBefore.size());


    }

    /**
     * Test of create method, of class de.h_da.library.component1.usecase.impl.UseCase1Impl.
     */
    @Test
    public void testCreateEntity1() {
        Entity1 entity1, entity1Created;

        // preparation
        entity1 = new Entity1();
        entity1.setAttribute1("att1");
        entity1.setAttribute2(DataType1.VALUE1);

        // execution
        entity1Created = useCase1.createEntity1(entity1);

        // evaluation
        assertNotNull(entity1Created);
        assertNotNull(entity1Created.getId());
        assertEquals("att1", entity1Created.getAttribute1());
        assertEquals(DataType1.VALUE1, entity1.getAttribute2());


    }

    @Test
    public void testCreateEntityNull() {
        try {
            useCase1.createEntity1(null);
        } catch (EJBException e) {
            assertEquals(LibraryRuntimeException.class, e.getCause().getClass());
        }
    }

}

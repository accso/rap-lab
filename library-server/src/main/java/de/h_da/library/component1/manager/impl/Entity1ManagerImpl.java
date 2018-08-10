/*
 * Entity1Facade.java
 *
 * Created on 10. September 2007, 14:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.h_da.library.component1.manager.impl;

import de.h_da.library.component1.entity.Entity1;
import de.h_da.library.component1.manager.Entity1Manager;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class Entity1ManagerImpl implements Entity1Manager {

    @PersistenceContext
    private EntityManager em;
    
    /** Creates a new instance of Entity1Facade */
    public Entity1ManagerImpl() {
    }
    
    public Entity1 create(Entity1 entity1) {
        em.persist(entity1);
        return entity1;
    }

    public void edit(Entity1 entity1) {
        em.merge(entity1);
    }

    public void destroy(Entity1 entity1) {
        em.remove(em.merge(entity1));
    }

    public Entity1 findById(Long id) {
        return (Entity1) em.find(Entity1.class, id);
    }

    public List findAll() {
        return em.createQuery("select object(o) from Entity1 as o").getResultList();
    }
    
}

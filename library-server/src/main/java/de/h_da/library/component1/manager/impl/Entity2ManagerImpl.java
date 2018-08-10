/*
 * Entity2ManagerImpl.java
 *
 * Created on 10. September 2007, 18:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.h_da.library.component1.manager.impl;

import de.h_da.library.component1.entity.Entity1;
import de.h_da.library.component1.entity.Entity2;
import de.h_da.library.component1.manager.Entity1Manager;
import de.h_da.library.component1.manager.Entity2Manager;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Entity2ManagerImpl implements Entity2Manager {
    
    @PersistenceContext
    private EntityManager em;
    
    @EJB()
    Entity1Manager entity1Manager;
    
    /**
     * Creates a new instance of Entity2ManagerImpl
     */
    public Entity2ManagerImpl() {
    }
    

     public Entity2 create(Entity2 entity2, Long idEntity1) {
        Entity1 entity1;
        
        entity1 = entity1Manager.findById(idEntity1);
        entity2.setEntity1(entity1);
        em.persist(entity2);
        return entity2;
    }
    
    public void edit(Entity2 entity2, Long idEntity1) {
        Entity1 entity1;

        entity1 = entity1Manager.findById(idEntity1);
        entity2.setEntity1(entity1);
        em.merge(entity2);
    }
    
    public void destroy(Entity2 entity2) {
        em.remove(em.merge(entity2));
    }
    
    public Entity2 findById(Long id) {
        return (Entity2) em.find(Entity2.class, id);
    }
    
    public List<Entity2> findByEntity1Id(Long idEntity1){
        return em.createQuery("select object(o) from Entity2 as o where o.entity1.id = :value").setParameter("value", idEntity1).getResultList();
    }
    
    public List findAll() {
        return em.createQuery("select object(o) from Entity2 as o").getResultList();
    }
    
}

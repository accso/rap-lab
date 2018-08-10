/*
 * Entity2Manager.java
 *
 * Created on 10. September 2007, 18:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.h_da.library.component1.manager;

import de.h_da.library.component1.entity.Entity2;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author humm
 */
@Local
public interface Entity2Manager {
    Entity2 create(Entity2 entity2, Long entity1Id);

    void destroy(Entity2 entity2);

    Entity2 findById(Long id);

    List findAll();

    void edit(Entity2 entity2, Long entity1Id);

    List<Entity2> findByEntity1Id(Long idEntity1);

    
}

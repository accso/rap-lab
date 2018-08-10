/*
 * Entity1FacadeLocal.java
 *
 * Created on 10. September 2007, 14:59
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.h_da.library.component1.manager;

import de.h_da.library.component1.entity.Entity1;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author humm
 */
@Local
public interface Entity1Manager {

    void edit(Entity1 entity1);

    void destroy(Entity1 entity1);

    Entity1 findById(Long id);

    List findAll();

    Entity1 create(Entity1 entity1);
    
}

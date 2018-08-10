
package de.h_da.library.component1.usecase;

import de.h_da.library.component1.entity.Entity1;
import java.util.List;
import javax.ejb.Local;


/**
 * This is the business interface for UseCase1 enterprise bean.
 */
@Local
public interface UseCase1 {
    List<Entity1> useCaseMethod1();

    Entity1 createEntity1(Entity1 entity1Record);
    
}

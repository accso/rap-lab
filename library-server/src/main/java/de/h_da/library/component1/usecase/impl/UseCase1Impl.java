/*
 * UseCase1Bean.java
 *
 * Created on 10. September 2007, 15:11
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package de.h_da.library.component1.usecase.impl;

import de.h_da.library.LibraryRuntimeException;
import de.h_da.library.component1.entity.Entity1;
import de.h_da.library.component1.manager.Entity1Manager;
import de.h_da.library.component1.usecase.UseCase1;
import de.h_da.library.component1.usecase.UseCase1Remote;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UseCase1Impl implements UseCase1, UseCase1Remote {
    
    @EJB()
    Entity1Manager entity1Manager;
    
    /** Creates a new instance of UseCase1Bean */
    public UseCase1Impl() {
    }
    
    public Entity1 createEntity1(Entity1 entity1Record){
        if(entity1Record == null)
            throw new LibraryRuntimeException("Entity must be set");

        return entity1Manager.create(entity1Record);
    }
    
    public List<Entity1> useCaseMethod1(){
        return entity1Manager.findAll();
    }
    
}

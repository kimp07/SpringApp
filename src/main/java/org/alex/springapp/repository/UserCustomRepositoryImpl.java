package org.alex.springapp.repository;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UserCustomRepositoryImpl implements UserCustomRepository {
    
    @PersistenceContext
    EntityManager manager;
    
    @Override
    public void updateUserByFields(long id, Map<String, Object> fields) {
        //TODO:
        //String sql = "UPDATE User u";
        
        //manager.createQuery(updateQuery);
        
    }

}

package org.alex.springapp.repository;

import java.util.Map;

public interface UserCustomRepository {
    
    void updateUserByFields(long id, Map<String, Object> fields);
    
}

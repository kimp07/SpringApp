package org.alex.springapp.repository;

import java.util.Map;

public interface CustomRepository<T> {
    
    int updateByFields(T entity, long id, Map<String, Object> fields);
}

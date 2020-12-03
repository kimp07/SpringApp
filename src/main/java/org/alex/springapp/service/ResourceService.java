package org.alex.springapp.service;

import java.util.List;
import org.alex.springapp.entity.Resource;

/**
 *
 * @author zamdirit
 */
public interface ResourceService {
    
    List<Resource> findAll();
    
    Resource findById(long id);
    
    Resource save(Resource resource);
    
    Resource save(String path);
    
    boolean deleteById(long id);
}

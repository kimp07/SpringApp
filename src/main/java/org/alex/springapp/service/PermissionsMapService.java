package org.alex.springapp.service;

import java.util.List;
import org.alex.springapp.entity.PermissionsMap;

/**
 *
 * @author zamdirit
 */
public interface PermissionsMapService {
     
    List<PermissionsMap> findAllByRoleId(long roleId);        
    
    PermissionsMap findById(long id);
    
    PermissionsMap save(PermissionsMap permissionsMap);
    
    boolean deleteById(long id);
    
    boolean deleteAllByRoleId(long roleId);
    
    List<String> findPermissionsByRoleId(long roleId);
}

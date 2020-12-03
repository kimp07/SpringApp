package org.alex.springapp.service;

import java.util.List;
import org.alex.springapp.entity.PermissionsMap;
import org.alex.springapp.entity.Role;

/**
 *
 * @author zamdirit
 */
public interface PermissionsMapService {
     
    List<PermissionsMap> findAllByRoleId(long roleId); 
    
    List<PermissionsMap> findAllByResourceId(long resourceId);
    
    PermissionsMap findById(long id);
    
    PermissionsMap save(PermissionsMap permissionsMap);
    
    boolean deleteById(long id);
    
    boolean deleteAllByRoleId(long roleId);
    
    List<String> findPermissionsByRoleId(long roleId);
    
    List<Role> findRolesByResourceId(long resourceId);
    
}

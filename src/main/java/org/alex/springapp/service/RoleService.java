package org.alex.springapp.service;

import java.util.List;
import org.alex.springapp.entity.Role;

/**
 *
 * @author zamdirit
 */
public interface RoleService {
    
    List<Role> findAll();
    
    Role findById(long id);

    Role findByRoleName(String roleName);
    
    List<Role> findAllEnabledRoles();
    
    List<Role> findAllDisabledRoles();
    
    Role save(Role role);
    
    boolean deleteById(long id);
}

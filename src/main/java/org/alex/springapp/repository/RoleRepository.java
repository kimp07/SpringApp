package org.alex.springapp.repository;

import java.util.List;
import org.alex.springapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author zamdirit
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    @Query(value = "SELECT r FROM Role r WHERE r.roleName = :roleName")
    Role findByRoleName(String roleName);
    
    @Query(value = "SELECT r FROM Role r WHERE r.roleDisable = 0")
    List<Role> findAllEnabledRoles();

    @Query(value = "SELECT r FROM Role r WHERE r.roleDisable = 1")
    List<Role> findAllDisabledRoles();        

}

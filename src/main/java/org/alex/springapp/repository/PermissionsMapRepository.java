package org.alex.springapp.repository;

import java.util.List;
import org.alex.springapp.entity.PermissionsMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author zamdirit
 */
public interface PermissionsMapRepository extends JpaRepository<PermissionsMap, Long> {
    
    @Query(value = "SELECT p FROM PermissionsMap p WHERE p.role.id = :roleId")
    List<PermissionsMap> findAllByRoleId(long roleId);
    
}

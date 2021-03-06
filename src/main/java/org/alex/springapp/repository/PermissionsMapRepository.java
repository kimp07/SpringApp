package org.alex.springapp.repository;

import java.util.List;
import org.alex.springapp.entity.PermissionsMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zamdirit
 */
@Repository
public interface PermissionsMapRepository extends JpaRepository<PermissionsMap, Long> {

    @Query(value = "SELECT p FROM PermissionsMap p WHERE p.role.id = :roleId")
    List<PermissionsMap> findAllByRoleId(@Param("roleId") long roleId);

    @Query(value = "SELECT p FROM PermissionsMap p WHERE p.resource.id = :resourceId")
    List<PermissionsMap> findAllByResourceId(@Param("resourceId") long resourceId);

    @Query(value
            = "SELECT p FROM PermissionsMap p WHERE p.resource.id = :resourceId AND p.role.roleDisable = 0")
    List<PermissionsMap> findAllByResourceIdAndRoleNotDisable(@Param("resourceId") long resourceId);

}

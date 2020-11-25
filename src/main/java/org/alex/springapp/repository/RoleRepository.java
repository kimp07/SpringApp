package org.alex.springapp.repository;

import java.util.List;
import java.util.Optional;
import org.alex.springapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zamdirit
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    @Query(value = "SELECT r FROM Role r WHERE r.roleName = :roleName")
    Optional<Role> findOneByRoleName(@Param("roleName") String roleName);
    
    @Query(value = "SELECT r FROM Role r WHERE r.roleDisable = 0")
    List<Role> findAllEnabledRoles();

    @Query(value = "SELECT r FROM Role r WHERE r.roleDisable = 1")
    List<Role> findAllDisabledRoles();        

}

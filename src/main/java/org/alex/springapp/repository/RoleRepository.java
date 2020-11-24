package org.alex.springapp.repository;

import org.alex.springapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author zamdirit
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    
}

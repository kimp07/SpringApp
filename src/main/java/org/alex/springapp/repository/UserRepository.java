package org.alex.springapp.repository;

import org.alex.springapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author zamdirit
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
}

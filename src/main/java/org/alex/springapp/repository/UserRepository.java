package org.alex.springapp.repository;

import java.util.List;
import org.alex.springapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author zamdirit
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    
    @Query(value = "SELECT u FROM User u WHERE u.userName = :userName")
    User findByUserName(String userName);

    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);    
    
    @Query(value = "SELECT u FROM User u WHERE u.role.id = :roleId")
    List<User> findAllByRoleId(long roleId);

    @Query(value = "SELECT u FROM User u WHERE u.role.id = :roleId")
    Page<User> findAllByRoleId(long roleId, Pageable pageable);
    
    @Query(value = "SELECT u FROM User u WHERE u.role.roleName = :roleName")
    List<User> findAllByRoleName(String roleName);

    @Query(value = "SELECT u FROM User u WHERE u.role.roleName = :roleName")
    Page<User> findAllByRoleName(String roleName, Pageable pageable);    
    
}

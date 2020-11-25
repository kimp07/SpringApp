package org.alex.springapp.repository;

import java.util.List;
import java.util.Optional;
import org.alex.springapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zamdirit
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    
    @Query(value = "SELECT u FROM User u WHERE u.userName = :userName")
    Optional<User> findByUserName(@Param("userName") String userName);

    @Query(value = "SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);    
    
    @Query(value = "SELECT u FROM User u WHERE u.role.id = :roleId")
    List<User> findAllByRoleId(@Param("roleId") long roleId);

    @Query(value = "SELECT u FROM User u WHERE u.role.id = :roleId")
    Page<User> findAllByRoleId(@Param("roleId") long roleId, Pageable pageable);
    
    @Query(value = "SELECT u FROM User u WHERE u.role.roleName = :roleName")
    List<User> findAllByRoleName(@Param("roleName") String roleName);

    @Query(value = "SELECT u FROM User u WHERE u.role.roleName = :roleName")
    Page<User> findAllByRoleName(@Param("roleName") String roleName, Pageable pageable);    
    
}

package org.alex.springapp.service;

import java.util.List;
import org.alex.springapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author zamdirit
 */
public interface UserService {
    
    List<User> findAll();
    
    Page<User> findAll(Pageable pageable);
    
    User findById(long id);
    
    List<User> findByRoleId(long roleId);

    Page<User> findByRoleId(long roleId, Pageable pageable);
    
    User findByUserName(String userName);
    
    User findByEmail(String email);
    
    User save(User user);
    
    boolean deleteById(long id);
}

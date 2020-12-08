package org.alex.springapp.service;

import java.util.List;
import java.util.Map;
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
    
    User findByUserNameAndPassword(String userName, String password);
    
    User findByEmail(String email);
    
    User findByEmailAndPassword(String email, String password);
    
    User save(User user);
    
    boolean deleteById(long id);
    
    int updateByFields(User user, long id, Map<String, Object> fields);
}

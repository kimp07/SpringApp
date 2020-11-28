package org.alex.springapp.config;

import org.alex.springapp.entity.User;
import org.alex.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author zamdirit
 */
@Component
public class ApplicationUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User " + userName + " not found!");
        }
        return ApplicationUserDetails.createFromUser(user);
    }
    
}

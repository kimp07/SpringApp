package org.alex.springapp.config;

import java.util.List;
import org.alex.springapp.config.jwt.JwtFilter;
import org.alex.springapp.entity.PermissionsMap;
import org.alex.springapp.entity.Role;
import org.alex.springapp.service.PermissionsMapService;
import org.alex.springapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author zamdirit
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private RoleService roleDAO;
    @Autowired
    private PermissionsMapService permissionsDAO;
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    ApplicationContext springAppContext;
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        if (!validateDefaultRoles()) {
            throw new RuntimeException("Defailt roles ADMIN and USER not defined!");
        }
        
        http.httpBasic().disable().csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/register", "/auth").permitAll();
        List<Role> enabledRoles = roleDAO.findAllEnabledRoles();
        for (Role role : enabledRoles) {
            String roleName = role.getRoleName();
            List<String> rolePermissions = permissionsDAO.findPermissionsByRoleId(role.getId());
            if (!rolePermissions.isEmpty()) {
                for (String pattern : rolePermissions) {
                    http.authorizeRequests().antMatchers(pattern).hasRole(roleName);
                }
                //http.authorizeRequests().antMatchers(rolePermissions.stream().toArray(String[]::new)).hasRole(roleName);
            }
        }
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    private boolean validateDefaultRoles() {
        Role defaultAdminRole = springAppContext.getBean("defaultAdminRole", Role.class);
        if (defaultAdminRole.getId() == 0) {
            return false;
        }
        Role defaultUserRole = springAppContext.getBean("defaultUserRole", Role.class);
        return !(defaultUserRole.getId() == 0);
    }

}

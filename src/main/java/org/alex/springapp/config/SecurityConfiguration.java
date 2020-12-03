package org.alex.springapp.config;

import java.util.List;

import org.alex.springapp.config.jwt.JwtFilter;
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
    
    public static final String DEFAULT_ENABLED_PATTERN = "/public/**";
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests().antMatchers("/register", "/auth").permitAll();
        List<Role> enabledRoles = roleDAO.findAllEnabledRoles();
        for (Role role : enabledRoles) {
            String roleName = role.getRoleName();
            List<String> rolePermissions = permissionsDAO.findPermissionsByRoleId(role.getId());
            if (!rolePermissions.isEmpty()) {
                for (String pattern : rolePermissions) {
                    http.authorizeRequests().antMatchers(pattern).hasRole(roleName);
                }
                //http.authorizeRequests().antMatchers(rolePermissions.stream().toArray(String[]::new)).hasRole(roleName)
            } else {
                http.authorizeRequests().antMatchers(DEFAULT_ENABLED_PATTERN).hasRole(roleName);
            }
        }
        http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}

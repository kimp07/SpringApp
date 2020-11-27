package org.alex.springapp.config;

import java.util.List;
import org.alex.springapp.config.jwt.JwtFilter;
import org.alex.springapp.entity.Role;
import org.alex.springapp.service.PermissionsMapService;
import org.alex.springapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry;
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/register", "/auth").permitAll();
        List<Role> enabledRoles = roleDAO.findAllEnabledRoles();
        for (Role role : enabledRoles) {
            String roleName = role.getRoleName();
            List<String> rolePermissions = permissionsDAO.findPermissionsByRoleId(role.getId());
            http
                    .authorizeRequests()
                    .antMatchers(rolePermissions.stream().toArray(String[]::new)).hasRole(roleName);
        }
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

}

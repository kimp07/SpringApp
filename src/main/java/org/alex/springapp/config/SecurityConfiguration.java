package org.alex.springapp.config;

import java.util.ArrayList;
import java.util.List;

import org.alex.springapp.config.jwt.JwtFilter;
import org.alex.springapp.entity.PermissionsMap;
import org.alex.springapp.entity.Resource;
import org.alex.springapp.service.PermissionsMapService;
import org.alex.springapp.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PermissionsMapService permissionsService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/api/register", "/api/auth", "/web/register", "/web/login").permitAll();
        List<Resource> resources = resourceService.findAll();
        for (Resource resource : resources) {
            List<PermissionsMap> permissions
                    = permissionsService.findAllByResourceIdAndRoleNotDisable(resource.getId());
            List<String> rolesArray = new ArrayList<>();
            permissions.forEach(permission -> rolesArray.add(permission.getRole().getRoleName()));
            http
                    .authorizeRequests()
                    .antMatchers(resource.getPath())
                    .hasAnyRole(rolesArray.stream().toArray(String[]::new));
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

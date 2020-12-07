package org.alex.springapp.config;

import org.alex.springapp.entity.Role;
import org.alex.springapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author zamdirit
 */
@Configuration
public class SpringAppConfig {

    @Value("${default.role.admin:ADMIN}")
    private String defaultAdminRoleName;

    @Value("${default.role.user:USER}")
    private String defaultUserRoleName;

    @Autowired
    private RoleService roleService;

    private Role getDefaultRoleByName(String roleName) {
        Role adminRoleFromDatabase = roleService.findByRoleName(roleName);
        if (adminRoleFromDatabase == null) {
            Role role = new Role();
            role.setRoleName(roleName);
            role.setRoleDisable(false);
            roleService.save(role);

            return role;
        }
        return adminRoleFromDatabase;
    }

    @Bean("defaultAdminRole")
    public Role getDefaultAdminRole() {
        return getDefaultRoleByName(defaultAdminRoleName);
    }

    @Bean("defaultUserRole")
    public Role getDefaultUserRole() {
        return getDefaultRoleByName(defaultUserRoleName);
    }

}

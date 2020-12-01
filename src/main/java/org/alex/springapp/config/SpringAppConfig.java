package org.alex.springapp.config;

import org.alex.springapp.entity.PermissionsMap;
import org.alex.springapp.entity.Role;
import org.alex.springapp.service.PermissionsMapService;
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
    private RoleService roleDAO;
    @Autowired
    private PermissionsMapService permissionsDAO;

    private Role getDefaultRoleByName(String roleName, String defaultPermission) {
        Role adminRoleFromDatabase = roleDAO.findByRoleName(roleName);
        if (adminRoleFromDatabase == null) {
            Role role = new Role();
            role.setRoleName(roleName);
            role.setRoleDisable(false);
            roleDAO.save(role);

            PermissionsMap permission = new PermissionsMap();
            permission.setRole(role);
            permission.setPermissionPath(defaultPermission);
            permissionsDAO.save(permission);

            return role;
        }
        return adminRoleFromDatabase;
    }

    @Bean("defaultAdminRole")
    public Role getDefaultAdminRole() {
        return getDefaultRoleByName(defaultAdminRoleName, "/**");
    }

    @Bean("defaultUserRole")
    public Role getDefaultUserRole() {
        return getDefaultRoleByName(defaultUserRoleName, "/public/**");
    }

}

package org.alex.springapp.dbservice;

import java.util.ArrayList;
import java.util.List;
import org.alex.springapp.entity.PermissionsMap;
import org.alex.springapp.entity.Role;
import org.alex.springapp.repository.PermissionsMapRepository;
import org.alex.springapp.service.PermissionsMapService;
import org.alex.springapp.service.ResourceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zamdirit
 */
@Service
public class PermissionsMapDefaultService implements PermissionsMapService {

    private final PermissionsMapRepository repository;

    private final ResourceService resourceService;

    private static final Logger LOG = LogManager.getLogger(PermissionsMapDefaultService.class);

    @Autowired
    public PermissionsMapDefaultService(PermissionsMapRepository repository, ResourceService resourceService) {
        this.repository = repository;
        this.resourceService = resourceService;
    }

    @Override
    public List<PermissionsMap> findAllByRoleId(long roleId) {
        return repository.findAllByRoleId(roleId);
    }

    @Override
    public List<PermissionsMap> findAllByResourceId(long resourceId) {
        return repository.findAllByResourceId(resourceId);
    }
    
    
    
    @Override
    public List<PermissionsMap> findAllByResourceIdAndRoleNotDisable(long resourceId) {
        return repository.findAllByResourceIdAndRoleNotDisable(resourceId);
    }

    @Override
    public PermissionsMap findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public PermissionsMap save(PermissionsMap permissionsMap) {
        if (permissionsMap == null) {
            LOG.error("PermissionsMap is null!");
            return null;
        }
        return repository.save(permissionsMap);
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        if (findById(id) == null) {
            LOG.error("PermissionsMap for id {} not found!", id);
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteAllByRoleId(long roleId) {
        List<PermissionsMap> permissions = findAllByRoleId(roleId);
        boolean operationResult = true;
        if (!permissions.isEmpty()) {
            for (PermissionsMap permission : permissions) {
                long id = permission.getId();
                if (!deleteById(id)) {
                    LOG.error("Can't delete all PermissionsMap for role id {}", roleId);
                    operationResult = false;
                }
            }
        }
        return operationResult;
    }

    @Override
    public List<String> findPermissionsByRoleId(long roleId) {
        List<PermissionsMap> permissions = findAllByRoleId(roleId);
        List<String> permissionsArray = new ArrayList<>();
        permissions.forEach(permission
                -> permissionsArray.add(resourceService.findById(permission.getResource().getId()).getPath())
        );
        return permissionsArray;
    }

    @Override
    public List<Role> findRolesByResourceId(long resourceId) {
        List<PermissionsMap> permissions = findAllByResourceId(resourceId);
        List<Role> roles = new ArrayList<>();
        permissions.forEach(permission
                -> roles.add(permission.getRole())
        );
        return roles;
    }

}

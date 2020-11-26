package org.alex.springapp.dao;

import java.util.ArrayList;
import java.util.List;
import org.alex.springapp.entity.PermissionsMap;
import org.alex.springapp.repository.PermissionsMapRepository;
import org.alex.springapp.service.PermissionsMapService;
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
public class PermissionsMapDAO implements PermissionsMapService {

    private final PermissionsMapRepository repository;

    private static final Logger LOG = LogManager.getLogger(PermissionsMapService.class);

    @Autowired
    public PermissionsMapDAO(PermissionsMapRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PermissionsMap> findAllByRoleId(long roleId) {
        return repository.findAllByRoleId(roleId);
    }

    @Override
    public PermissionsMap findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public PermissionsMap save(PermissionsMap permissionsMap) {
        return repository.save(permissionsMap);
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        if (findById(id) == null) {
            LOG.error("PermissionsMap for id " + id + " not found!");
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteAllByRoleId(long roleId) {
        ArrayList<PermissionsMap> permissions = new ArrayList<>(findAllByRoleId(roleId));
        boolean operationResult = true;
        if (permissions.size() > 0) {
            for (PermissionsMap permission : permissions) {
                long id = permission.getId();
                if (!deleteById(id)) {
                    LOG.error("Can't delete PermissionsMap for id " + id);
                    operationResult = false;
                }
            }
        }
        return operationResult;
    }

    @Override
    public List<String> findPermissionsByRoleId(long roleId) {
        ArrayList<PermissionsMap> permissions = new ArrayList<>(findAllByRoleId(roleId));
        ArrayList<String> permissionsArray = new ArrayList<>();
        permissions.forEach((permission) -> {
            permissionsArray.add(permission.getPermissionPath());
        });
        return permissionsArray;
    }

}

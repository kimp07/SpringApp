package org.alex.springapp.dao;

import java.util.List;
import org.alex.springapp.entity.Role;
import org.alex.springapp.repository.RoleRepository;
import org.alex.springapp.service.RoleService;
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
public class RoleDAO implements RoleService {

    private final RoleRepository repository;
    
    private static final Logger LOG = LogManager.getLogger(RoleService.class);
    
    @Autowired
    public RoleDAO(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Role findById(long id) {
        Role role = repository.findById(id).orElse(null);
        return role;
    }

    @Override
    public Role findByRoleName(String roleName) {
        Role role = repository.findOneByRoleName(roleName).orElse(null);
        return role;
    }

    @Override
    public List<Role> findAllEnabledRoles() {
        return repository.findAllEnabledRoles();
    }

    @Override
    public List<Role> findAllDisabledRoles() {
        return repository.findAllDisabledRoles();
    }

    @Override
    @Transactional
    public Role save(Role role) {
        if (role == null) {
            LOG.error("Role is null!");
            return null;
        }
        if (role.getId() == 0) { // insert new record
            Role roleFromBase = findByRoleName(role.getRoleName());
            if (roleFromBase != null) {
                LOG.error("Role with roleName " + role.getRoleName() + " exists!");
                return role;
            }
        } else if (role.getRoleName().isEmpty()) {
            Role roleFromBase = findById(role.getId()); // if roleName empty, try to restore from database
            if (roleFromBase != null) {
                role.setRoleName(roleFromBase.getRoleName());
            } else {
                LOG.error("Field roleName cannot e empty!");
                return role;
            }            
        }
        return repository.save(role);
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        
        return true;
    }

}

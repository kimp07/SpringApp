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
        return repository.findById(id).orElse(null);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return repository.findOneByRoleName(roleName).orElse(null);
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
        return repository.save(role);
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        if (findById(id) == null) {
            LOG.error("Role for id " + id + " not found!");
            return false;
        }
        repository.deleteById(id);
        return true;
    }

}

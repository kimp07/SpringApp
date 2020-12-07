package org.alex.springapp.dao;

import java.util.List;
import org.alex.springapp.entity.Resource;
import org.alex.springapp.repository.ResourceRepository;
import org.alex.springapp.service.ResourceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author zamdirit
 */
@Service
public class ResourceDAO implements ResourceService {
    
    private final ResourceRepository repository;
    
    private static final Logger LOG = LogManager.getLogger(ResourceDAO.class);
    
    @Autowired
    public ResourceDAO(ResourceRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public List<Resource> findAll() {
        return repository.findAll();
    }

    @Override
    public Resource findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Resource save(Resource resource) {
        return repository.save(resource);
    }

    @Override
    public Resource save(String path) {
        Resource resource = new Resource();
        resource.setPath(path);
        return repository.save(resource);
    }

    @Override
    public boolean deleteById(long id) {
        if (findById(id) == null) {
            LOG.error("Resource for id {} not found!", id);
            return false;
        }
        repository.deleteById(id);
        return true;
    }
    
}

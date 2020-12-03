package org.alex.springapp.repository;

import org.alex.springapp.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zamdirit
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    
}

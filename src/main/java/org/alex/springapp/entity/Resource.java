package org.alex.springapp.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author zamdirit
 */
@Entity
@Table(name = "resources")
@Getter
@Setter
public class Resource {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "path")
    private String path;
    @OneToMany(mappedBy = "resource", fetch = FetchType.LAZY)
    private List<PermissionsMap> permissions;
}

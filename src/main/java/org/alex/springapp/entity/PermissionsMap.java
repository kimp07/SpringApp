package org.alex.springapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "permissions_map")
@Getter
@Setter
public class PermissionsMap {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;
    @OneToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "id", nullable = false)
    private Resource resource;

}

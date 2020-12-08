package org.alex.springapp.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8305754714791240103L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_disable")
    private boolean roleDisable;
    @Transient
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> users;
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<PermissionsMap> permissions;
}

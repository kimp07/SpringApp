package org.alex.springapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Alex
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "enabled", columnDefinition = "tinyint(1) default 1")
    private boolean enabled;
    @Column(name = "non_locked", columnDefinition = "tinyint(1) default 1")
    private boolean nonLocked;
    @Column(name = "non_expired", columnDefinition = "tinyint(1) default 1")
    private boolean nonExpired;
    @Column(name = "credentials_non_expired", columnDefinition = "tinyint(1) default 1")
    private boolean credentialsNonExpired;
    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

}

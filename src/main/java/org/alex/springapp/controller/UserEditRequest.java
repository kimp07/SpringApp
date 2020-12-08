package org.alex.springapp.controller;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author zamdirit
 */
@Data
@Getter
@Setter
public class UserEditRequest {
    
    private Long id;
    private Long roleId;
    
    private String email;
    private String fullName;
    
    private Boolean enabled;
    private Boolean nonLocked;
    private Boolean nonExpired;
    private Boolean credentialsNonExpired;
    
}

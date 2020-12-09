package org.alex.springapp.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.alex.springapp.controller.request.AbstractRequestData;
import org.alex.springapp.controller.request.RequestField;

/**
 *
 * @author zamdirit
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Getter
@Setter
public class UserEditRequest extends AbstractRequestData {
    
    private Long id;    
    private Long roleId;
    @RequestField
    private String email;
    @RequestField
    private String fullName;
    @RequestField
    private Boolean enabled;
    @RequestField
    private Boolean nonLocked;
    @RequestField
    private Boolean nonExpired;
    @RequestField
    private Boolean credentialsNonExpired;
    
}

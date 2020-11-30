package org.alex.springapp.controller;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author zamdirit
 */
@Data
public class RegistrationRequest {
    
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    private String fullName;
        
}

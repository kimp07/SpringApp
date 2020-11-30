package org.alex.springapp.controller;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 *
 * @author zamdirit
 */
@Data
public class AuthRequest {
    
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    
}

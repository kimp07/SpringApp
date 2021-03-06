package org.alex.springapp.controller;

import java.util.Map;
import javax.validation.Valid;
import org.alex.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.alex.springapp.entity.User;
import org.alex.springapp.entity.Role;
import org.alex.springapp.service.RoleService;

/**
 *
 * @author zamdirit
 */
@RestController
public class MainController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    
    @GetMapping("/public/index")
    public String publicIndexPage() {
        return "Public index page: access is allowed";
    }
    
    @GetMapping("/admin/index")
    public String privateIndexPage() {
        return "Private index page: access is allowed";
    }
    
    @PostMapping("/admin/edituser")
    public String editUser(@RequestBody @Valid UserEditRequest userData) {
        if (userData.getId() == null) {
            return "Error: id can not be null!";
        }        
        User user = userService.findById(userData.getId());
        if (user == null) {
            return "Error: user for id " + userData.getId() + " not found!";
        }
        Map<String, Object> fields = userData.getFilledFields();
        if (userData.getRoleId() != null) {
            Role roleFromDatabase = roleService.findById(userData.getRoleId());
            if (roleFromDatabase == null) {
                return "Error: role for id " + userData.getRoleId() + " not found!";
            }
            fields.put("role", roleFromDatabase);
        }
        if (!fields.isEmpty()) {
            userService.updateByFields(user, userData.getId(), fields);
        }        
        return "User edited";
    }
    
}

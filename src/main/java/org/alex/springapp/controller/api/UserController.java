package org.alex.springapp.controller.api;

import javax.validation.Valid;
import org.alex.springapp.controller.ResponseMessage;
import org.alex.springapp.controller.UserActions;
import org.alex.springapp.controller.UserEditRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zamdirit
 */

@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/admin/edituser")
    public String editUser(@RequestBody @Valid UserEditRequest userData) {
        ResponseMessage response = new UserActions().editUser(userData);
        return response.getMessage();
    }

    
}

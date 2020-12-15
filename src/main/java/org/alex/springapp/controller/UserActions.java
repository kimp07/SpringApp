package org.alex.springapp.controller;

import java.util.Map;
import org.alex.springapp.entity.Role;
import org.alex.springapp.entity.User;
import org.alex.springapp.service.RoleService;
import org.alex.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author zamdirit
 */
public class UserActions {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    public ResponseMessage editUser(UserEditRequest userData) {
        if (userData.getId() == null) {
            return new ResponseMessage(
                    ResponseMessage.ERROR,
                    "Error: id can not be null!",
                    userData);
        }
        User user = userService.findById(userData.getId());
        if (user == null) {
            return new ResponseMessage(
                    ResponseMessage.ERROR,
                    "Error: user for id " + userData.getId() + " not found!",
                    userData);
        }
        Map<String, Object> fields = userData.getFilledFields();
        if (userData.getRoleId() != null) {
            Role roleFromDatabase = roleService.findById(userData.getRoleId());
            if (roleFromDatabase == null) {
                return new ResponseMessage(
                        ResponseMessage.ERROR,
                        "Error: role for id " + userData.getRoleId() + " not found!",
                        userData);
            }
            fields.put("role", roleFromDatabase);
        }
        if (!fields.isEmpty()) {
            userService.updateByFields(user, userData.getId(), fields);
        }
        return new ResponseMessage(ResponseMessage.SUCCESS, "User edited", userData);
    }

}

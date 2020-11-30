package org.alex.springapp.controller;

import javax.validation.Valid;
import org.alex.springapp.config.jwt.JwtProvider;
import org.alex.springapp.entity.Role;
import org.alex.springapp.entity.User;
import org.alex.springapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zamdirit
 */
@RestController
public class AuthController {

    @Autowired
    private UserService userDAO;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private ApplicationContext context;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest requestBody) {
        String userName = requestBody.getUserName();
        String password = requestBody.getPassword();
        String email = requestBody.getEmail();
        String fullName = requestBody.getFullName();
        if (fullName == null) {
            fullName = "";
        }
        Role defaultRole = context.getBean("defaultUserRole", Role.class);

        if (userDAO.findByUserName(userName) != null) {
            return "User with name " + userName + " already exists!";
        }
        if (userDAO.findByEmail(email) != null) {
            return "User with email " + email + " already exists!";
        }

        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setRole(defaultRole);
        user.setFullName(fullName);
        user.setPassword(password);
        user.setEnabled(true);
        user.setNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setNonLocked(true);
        userDAO.save(user);

        return "REGISTERED";
    }

    @PostMapping("/auth")
    public String logIn(@RequestBody @Valid AuthRequest requestBody) {
        String userName = requestBody.getUserName();
        String password = requestBody.getPassword();

        User user = userDAO.findByUserNameAndPassword(userName, password);
        if (user != null) {
            String token = jwtProvider.generateToken(userName);
            return token;
        }
        return "Access denied: invalid user name or password!";
    }
}

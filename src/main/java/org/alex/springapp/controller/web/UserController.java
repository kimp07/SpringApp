package org.alex.springapp.controller.web;

import java.util.Optional;
import javax.validation.Valid;
import org.alex.springapp.controller.AuthRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author zamdirit
 */
@RequestMapping("/web")
public class UserController {
    
    @GetMapping("/admin/allUsers")
    public String showAll(
            @RequestParam(name = "page", required = false) Optional<Integer> page,
            Model model) {
        
        return "all-users";
    }
    
    @GetMapping("/admin/editUserForm")
    public String editUserForm(
            @RequestParam(name = "userId", required = false) Optional<Long> userId,
            Model model) {
        return "";
    }
    
    @GetMapping("/web/authForm")
    public String authForm() {
        return "login";
    }
    
    @PostMapping("/web/auth")
    public String auth(@RequestBody @Valid AuthRequest requestBody) {
        return "redirect:web/index";
    }

    
}

package org.alex.springapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zamdirit
 */
@RestController
public class MainController {
    
    @GetMapping("/public/index")
    public String publicIndexPage() {
        return "Public index page: access is allowed";
    }
    
    @GetMapping("/admin/index")
    public String privateIndexPage() {
        return "Private index page: access is allowed";
    }
    
}

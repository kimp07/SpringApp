package org.alex.springapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author zamdirit
 */
@Controller
@RequestMapping(value = "/web")
public class MainWebController {
    
    @GetMapping("/index")
    public String indexPage() {
        return "/index";
    }
    
    
}

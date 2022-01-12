package app.controller;

import app.model.Role;
import app.model.User;
import app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/")
public class MainController {

    private final AppService appService;

    @Autowired
    public MainController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("")
    public String getHomePage() {
        return "home";
    }

    @GetMapping("/new")
    public String getNewUserPage(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/new")
    public String getUserPage(@ModelAttribute("user") User user) {
        Set<Role> roles = new HashSet<>();
//        roles.add(new Role("ROLE_USER"));
        roles.add(appService.findRoleByRole("ROLE_USER"));
        user.setEnabled(true);
        user.setRoles(roles);
        System.out.println(user);
        return appService.saveUser(user) ? "redirect:/user" : "/new";
    }
}

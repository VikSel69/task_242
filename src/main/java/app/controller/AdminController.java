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
@RequestMapping("/admin")
public class AdminController {

    private final AppService appService;

    @Autowired
    public AdminController(AppService appService) {
        this.appService = appService;
    }


    @GetMapping
    public String getAdminPage(Model model) {
        model.addAttribute("users", appService.findAllUsers());
        return "admin";
    }

    @GetMapping("/{id}/edit")
    public String editUserPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", appService.findUser(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam(defaultValue = "false") boolean checkbox_admin,
                             @RequestParam(defaultValue = "false") boolean checkbox_user, @RequestParam(defaultValue = "false") boolean checkbox_enabled) {
        Set<Role> roles = new HashSet<>();
        if (checkbox_admin) {roles.add(new Role("ROLE_ADMIN"));}
        if (checkbox_user) {roles.add(new Role("ROLE_USER"));}
        user.setEnabled(checkbox_enabled);
        user.setRoles(roles);
        return appService.updateUser(user) ? "redirect:/admin" : "/home";
    }

}

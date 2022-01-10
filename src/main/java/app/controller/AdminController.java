package app.controller;

import app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("allRoles", appService.findAllRoles());
        return "edit";
    }

}

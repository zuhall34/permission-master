package com.task.permission.controller;

import com.task.permission.model.Manager;
import com.task.permission.model.Permission;
import com.task.permission.model.Person;
import com.task.permission.model.Role;
import com.task.permission.service.PermissionService;
import com.task.permission.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {
    private final PersonService personService;
    private final PermissionService permissionService;


    public MainController(PersonService personService, PermissionService permissionService) {
        this.personService = personService;
        this.permissionService = permissionService;
    }

    /**
     * Giriş ekranı
     * @return index.html
     */
    @GetMapping("/")
    public String login(){
        return "index";
    }

    /**
     * Kimlik doğrulama sonrası açılacak sayfa
     * @param model kullanıcı bilgilerini gönderen sınıf
     * @param principal kimlik doğrulaması yapan kullanıcıya ait bilgiler
     * @return main.html
     */
    @GetMapping("/main")
    public String mainPage(Model model,Principal principal){
        //Spring security ile authenticate olmuş person bulunur.
        Person temp= personService.findByUsername(principal.getName());
        /**
         * Giriş yapan kullanıcının rolü MANAGER ise ona bağlı çalışanların izin bilgileri liste
         * olarak model nesnesine eklenir
         */
        if(temp.getRole().equals(Role.ROLE_MANAGER)){
            List<Permission> permissions= personService.findByManager((Manager)temp).stream().map(e->e.getPermissions()).findAny().get();
            model.addAttribute("permissionList",permissions);
        }
        model.addAttribute("person",temp);
        return "main";
    }

    /**
     * İzin isteği sayfasına yönlendirir
     * @param model
     * @param principal
     * @return permission.html
     */
    @GetMapping("/permission")
    public String permission(Model model,Principal principal){
        model.addAttribute("permission",new Permission());
        model.addAttribute("person",personService.findByUsername(principal.getName()));
        return "permission";
    }


}

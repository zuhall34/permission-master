package com.task.permission.controller;

import com.task.permission.model.Permission;
import com.task.permission.model.Person;
import com.task.permission.service.PermissionService;
import com.task.permission.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class PermissionController {
    private final PermissionService permissionService;
    private final PersonService personService;

    public PermissionController(PermissionService permissionService, PersonService personService) {
        this.permissionService = permissionService;
        this.personService = personService;
    }

    /**
     * Yönetici tarafından bir izinin onaylanması için kullanılan method
     * @param permissionId
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/permission/submit/{permissionId}")
    public String submitPermission(@PathVariable("permissionId")Integer permissionId, Model model, Principal principal){
        Permission temp=permissionService.findPermissionById(permissionId);
        temp.setPermissionStatus("ONAYLANDI");
        permissionService.savePermisson(temp);
        model.addAttribute("person",personService.findByUsername(principal.getName()));
        return "redirect:/main?success";
    }

    /**
     * Yönetici tarafından bir izini iptal etmek için kullanılan method
     * @param permissionId
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/permission/deny/{permissionId}")
    public String denyPermission(@PathVariable("permissionId")Integer permissionId, Model model, Principal principal){
        Permission temp=permissionService.findPermissionById(permissionId);
        temp.setPermissionStatus("REDDEDİLDİ");
        permissionService.savePermisson(temp);
        model.addAttribute("person",personService.findByUsername(principal.getName()));
        return "redirect:/main?deny";
    }

    /**
     * Çalışanların izin isteklerini gönderdikleri ve sisteme kaydının yapıldığı method.
     * @param permission client tarafından gönderilen Permission nesnesi
     * @param model Kayıt işlemi bittikten sonra main sayfasına yönlendirilirken kullanıcı bilgilerinin bulunup
     *              model nesnesine eklenmesi isin kullanıyoruz.
     * @param principal
     * @return main.html sayfasına yönlendirmeyi gerçekleştirir.
     */
    @PostMapping("/permission")
    public String savePermission(@ModelAttribute("permission") Permission permission , Model model, Principal principal){
        Person temp=personService.findByUsername(principal.getName());

        permission.setPerson(temp); //istenen izine kimin izin istediği bilgisi girilir.
        permission.setPermissionStatus("BEKLEMEDE"); //çalışan izin isteğini ilk girdiğinde status olarak BEKLEMEDE bilgisi kayıt edilir.
        permissionService.savePermisson(permission);
        model.addAttribute("person",temp);
        return "redirect:main";
    }

}

package com.task.permission.config;

import com.task.permission.model.Employee;
import com.task.permission.model.Manager;
import com.task.permission.model.Person;
import com.task.permission.model.Role;
import com.task.permission.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoadData implements CommandLineRunner {
    private final PersonService personService;

    public LoadData(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void run(String... args) throws Exception {
        /**
         * Rastgele çalışan ve yönetici bilgilerinin db'ye kaydedilmesi için oluşturulmuş bilgiler
         */
        Employee employee=new Employee();
        employee.setUsername("calisan1");
        employee.setPassword("test1");
        employee.setName("Ali");
        employee.setSurName("Yılmaz");
        employee.setTitle("işçi");
        employee.setRole(Role.ROLE_EMPLOYEE);

        Manager manager=new Manager();
        manager.setUsername("yonetici1");
        manager.setPassword("test1");
        manager.setName("Hasan");
        manager.setSurName("Yıldız");
        manager.setRole(Role.ROLE_MANAGER);
        manager.addEmployee(employee);

        employee.setManager(manager);
        List<Person> personList = new ArrayList<>();
        personList.add(employee);
        personList.add(manager);
        personService.saveAll(personList);



    }
}

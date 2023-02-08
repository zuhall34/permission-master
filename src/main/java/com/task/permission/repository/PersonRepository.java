package com.task.permission.repository;

import com.task.permission.model.Employee;
import com.task.permission.model.Manager;
import com.task.permission.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Integer> {
    //Optinal olarak kullanıcı adına bağlı olarak Çalışanın getirilmesi
    Optional<Person> findByUsername(String username);
    // Bir yöneticiye bağlı olan çalışanların getirilmesi
    List<Employee> findByManager(Manager manager);
}

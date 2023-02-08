package com.task.permission.service;

import com.task.permission.model.Employee;
import com.task.permission.model.Manager;
import com.task.permission.model.Person;
import com.task.permission.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Bütün kullanıcıları getiren method
     * @return
     */
    public List<Person> findAll(){
        return personRepository.findAll();
    }

    /**
     * Birden çok kullanıcıyı kaydeden method
     * @param personList
     */
    public void saveAll(List<Person> personList){
        personRepository.saveAll(personList);
    }

    /**
     * Kullanıcı adına göre bir kullanıcının db'den bulunması
     * @param username
     * @return Person
     */
    public Person findByUsername(String username){
        return personRepository.findByUsername(username)
                .orElseThrow(()-> new NoSuchElementException(username+" isimli eleman bulunamamıştır"));
    }

    /**
     * Bir yöneticiye bağlı olan tüm çalışanların db'den getirilmesi
     * @param manager
     * @return
     */
    public List<Employee> findByManager(Manager manager){
        return personRepository.findByManager(manager);
    }
}

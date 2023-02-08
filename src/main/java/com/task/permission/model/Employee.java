package com.task.permission.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee extends Person {
    /**
     * Çalışana ait bilgilerin (alanların) tutulduğu sınıf, Person sınıfından extend edilmiştir.
     *
     */
    private String name;
    private String surName;
    private String title;

    /**
     * Bir manager'ın altında çalışan pek çok çalışan olabilir bu ilişki OneToMany (Manager)
     * ManyToOne(Employee) sınıflarında kurulmuştur.
     */
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

}

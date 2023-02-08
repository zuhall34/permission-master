package com.task.permission.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Manager extends Person {

    /**
     * Yöneticiye ait bilgiler
     */
    private String name;
    private String surName;

    /**
     * Yöneticinin altında çalışan çalışanların arasındaki ilişkinin kurulduğu alan
     */
    @OneToMany(mappedBy = "manager",cascade = CascadeType.ALL)
    private List<Employee> employeeList=new ArrayList<>();



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

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void addEmployee(Employee employee){
        this.employeeList.add(employee);
    }

}

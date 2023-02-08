package com.task.permission.model;

import javax.persistence.*;

@Entity
public class Permission {
    /**
     * İzin bilgilerinin tutulduğu sınıf
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Bir kullanıcının pek çok izni olabilir dolaysıyla Person sınıfındaki OneToMany
     * annotasyonun karşılığını oluşturan ManyToOne annotation'ı.
     */
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    private String permissionReason;
    private String day;
    private String permissionStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPermissionReason() {
        return permissionReason;
    }

    public void setPermissionReason(String permissionReason) {
        this.permissionReason = permissionReason;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(String permissionStatus) {
        this.permissionStatus = permissionStatus;
    }
}

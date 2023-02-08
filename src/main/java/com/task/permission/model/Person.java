package com.task.permission.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Person {
    /**
     * Employee ve manager sınıfları Person sınıfından türetilmiştir.
     * Dolaysıyla her iki sınıfın ortak alanları Person sınıfında ele alınmıştır.
     * Bu sınıfta giriş bilgileri ve kullanıcıya ait izin bilgilerinin OneToMany ilişkisi ile
     * belirtilmesi aradaki ilişkinin (foreign key) kurulması sağlanmıştır.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;

    //Spring security tarafından filtreleme işlemlerinde kullanılmak üzere rol tanımı yapılmıştır.
    private Role role;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Permission> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}

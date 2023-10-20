package com.jonathan.users.domain;

import jakarta.persistence.*;
import lombok.Data;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Table(name = "users")
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 411214523;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String username;

    @Column(length = 60)
    private String password;

    private Boolean enabled;
    private String name;
    private String lastname;

    @Column(unique = true, length = 100)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "rol_id"})}
    )
    private List<Rol> roles;


}

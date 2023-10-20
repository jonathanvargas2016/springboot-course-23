package com.jonathan.users.domain;

import jakarta.persistence.*;
import lombok.Data;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Rol implements Serializable {

    private static final long serialVersionUID = 411214523;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 30)
    private String name;


}

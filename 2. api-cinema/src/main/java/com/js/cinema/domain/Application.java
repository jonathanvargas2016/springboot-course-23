package com.js.cinema.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.js.cinema.domain.enums.AppType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "application")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_APP", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Size(max = 200)
    @NotNull
    @Column(name = "DESCRIPTION_APP", nullable = false, length = 200)
    private String descriptionApp;

    @NotNull
    @Column(name = "APP_TYPE", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
  //  @JsonIgnore
    private AppType appType;

}
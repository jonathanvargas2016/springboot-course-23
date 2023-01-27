package com.js.cinema.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "release_app")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReleaseApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RELEASE", nullable = false)
    private Long id;

    @Size(max = 10)
    @NotNull
    @Column(name = "RELEASE_VERSION", nullable = false, length = 10)
    private String releaseVersion;

    @Column(name = "RELEASE_DATE")
    private LocalDate releaseDate;

    @Size(max = 200)
    @NotNull
    @Column(name = "DESCRIPTION_RELEASE", nullable = false, length = 200)
    private String descriptionRelease;

}
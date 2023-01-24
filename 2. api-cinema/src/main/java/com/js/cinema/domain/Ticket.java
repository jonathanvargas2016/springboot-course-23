package com.js.cinema.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TICKET", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_APP", nullable = false)
    private Application application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_RELEASE")
    private ReleaseApp release;

    @Size(max = 100)
    @NotNull
    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @Size(max = 200)
    @Column(name = "DESCRIPTION_TITLE", length = 200)
    private String descriptionTitle;

    @Size(max = 20)
    @NotNull
    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;


}
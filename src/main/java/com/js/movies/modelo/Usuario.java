package com.js.movies.modelo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @Column(name = "ID_USUARIO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SUSCRIPCION")
    private Suscripcion idSuscripcion;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "APELLIDO", nullable = false, length = 100)
    private String apellido;

    @Column(name = "CORREO", nullable = false, length = 100)
    private String correo;

    @Column(name = "CONTRAENIA_", nullable = false, length = 30)
    private String contraenia;

    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;

    @Column(name = "ESTADO_USUARIO", nullable = false)
    private Short estadoUsuario;

    @Column(name = "FECHANACIMIENTO", nullable = false)
    private LocalDate fechanacimiento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Suscripcion getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(Suscripcion idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraenia() {
        return contraenia;
    }

    public void setContraenia(String contraenia) {
        this.contraenia = contraenia;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Short getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Short estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

}
package com.informatorio.myblog.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 4)
    private String title;


    private String descripcion;

    private String contenido;

    private Date fechaCreacion;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name=id)
    private User autor;

    private boolean publicado;

    



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return this.contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public User getAutor() {
        return this.autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public boolean getPublicado() {
        return this.publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }




}

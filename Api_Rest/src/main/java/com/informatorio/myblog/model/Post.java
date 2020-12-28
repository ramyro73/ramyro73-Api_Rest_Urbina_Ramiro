package com.informatorio.myblog.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 4)
    private String titulo;

    private String descripcion;

    private String contenido;

    private LocalDate creationDate = LocalDate.now();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autorPost", referencedColumnName = "idUser")
    private User autorPost;

    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idComment")
    private List<Comment> comentarios = new ArrayList<>();

    private boolean publicado;

    public Long getIdPost() {
        return idPost;
    }

    

    public void setId(Long idPost) {
        this.idPost = idPost;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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


    public LocalDate getFechaCreacion() {
        return creationDate;
    }

    public void setFechaCreacion(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


    public User getAutorPost() {
        return this.autorPost;
    }

    public void setAutorPost(User autorPost) {
        this.autorPost = autorPost;
    }

    

    public boolean getPublicado() {
        return this.publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public void addComment(Comment comment) {
		this.comentarios.add(comment);
		comment.setPostAlQuePertenece(this);
    }


    /* public List<Comment> getComments() {
        return comentarios;
    }

    public void setComments(List<Comment> comments) {
        this.comentarios = comments;
    } */
    
    




}
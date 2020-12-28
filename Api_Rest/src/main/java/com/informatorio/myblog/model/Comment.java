package com.informatorio.myblog.model;

import java.io.Serializable;
import java.time.LocalDate;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComment;

    private LocalDate creationDate = LocalDate.now();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autorComment", referencedColumnName = "idUser")
    private User autorComment;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idPost", referencedColumnName = "idPost")
    private Post idPost;

    @Size(max = 200)
    private String comentario;

    public Long getIdComment() {
        return this.idComment;
    }

    

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }



    public LocalDate getFechaCreacion() {
        return creationDate;
    }

    public void setFechaCreacion(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public User getAutorComment() {
        return this.autorComment;
    }

    public void setAutorComment(User autorComment) {
        this.autorComment = autorComment;
    }


    public Post getPostAlQuePertenece() {
        return idPost;
    }

    public void setPostAlQuePertenece(Post idPost) {
        this.idPost = idPost;
    }

    public String getComment() {
        return this.comentario;
    }

    public void setComment(String comentario) {
        this.comentario = comentario;
    }

    

}

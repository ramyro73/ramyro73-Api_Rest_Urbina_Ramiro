package com.informatorio.myblog.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(unique=true)
    private String userName;


	@Column(nullable = false)
  	@NotBlank
  	@Size(min = 4)
    private String nombre;
    
    private String apellido;

    @Column(unique=true)
    private String mail;

    private String password;


    private String ciudad;

    private String provincia;

	private String pais;
	
	@OneToMany
	private List<Post> posts = new ArrayList<>();

	@OneToMany
	private List<Comment> comments = new ArrayList<>();
	
	private LocalDate creationDate = LocalDate.now();



	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

    public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



    public void addPost(Post post) {
		this.posts.add(post);
		post.setAutorPost(this);
	}

	public void addComment(Comment comment) {
		this.comments.add(comment);
		comment.setAutorComment(this);
	}
	
	public LocalDate getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(LocalDate creationDate) {
		
		this.creationDate = creationDate;
	 
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {

		this.posts = posts;
	}


	/* public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {

		this.comments = comments;
	} */
	
	
	

    
    
}

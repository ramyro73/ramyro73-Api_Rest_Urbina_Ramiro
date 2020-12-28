package com.informatorio.myblog.controller;

import com.informatorio.myblog.model.Comment;
import com.informatorio.myblog.model.Post;
import com.informatorio.myblog.repository.CommentRepository;
import com.informatorio.myblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    /* @Autowired
    private UserRepository userRepository; */

    @Autowired
    private CommentRepository commentRepository;

    //POST Crear un POST
    @PostMapping("/Post")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

    //DELETE Borrar un Post segun el ID
    @DeleteMapping("/Post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        Post postDelete = postRepository.getOne(postId);
        postRepository.delete(postDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //PUT para modificar un POST segun ID
    @PutMapping("/Post/{postId}")
    public ResponseEntity<?> editPost(@PathVariable Long postId, @Valid @RequestBody Post post) {
        Post postEdit = postRepository.getOne(postId);
        postEdit.setTitulo(post.getTitulo());
        postEdit.setDescripcion(post.getDescripcion());
        postEdit.setContenido(post.getContenido());
        postEdit.setPublicado(post.getPublicado());
        
        return new ResponseEntity<>(postRepository.save(postEdit), HttpStatus.OK);
    }
    
    
    //GET Todos los POST
    @GetMapping ("/Post")
    public ResponseEntity<?> getPosts() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    //buscar por palabra dada
    
    @GetMapping("/Post/like")
    public ResponseEntity<?> buscarPostLike(@RequestParam String titulo) {
        //findByTitleContains
        List <Post> posts = postRepository.findByTituloContains(titulo);
        
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    //post sin publicar
    @GetMapping("/Post/publicado")
    public ResponseEntity<?> buscarPostSinPublicar(@RequestParam Boolean publicado) {
        //findByTitleContains
        List <Post> posts = postRepository.findByPublicadoFalse();
        
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }



    @PostMapping("/Post/{postId}/comment")
    public ResponseEntity<?> createCommentGivenPost(@PathVariable Long postId, @RequestBody Comment comment) {
    Post  post = postRepository.getOne(postId);
    post.addComment(comment);
    return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.CREATED);
  }
  
}

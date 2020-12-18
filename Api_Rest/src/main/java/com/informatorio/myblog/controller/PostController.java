package com.informatorio.myblog.controller;

import com.informatorio.myblog.model.Post;
import com.informatorio.myblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/Post")
public class PostController {

    //private Map<Integer, String> postRepository = inicializarRepo();
    @Autowired
    private PostRepository postRepository;

    //GET Todos los POST
    @GetMapping // ~ /api/v1/post
    public ResponseEntity<?> getPosts() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    //POST Crear un POST
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

    //PUT para modificar un POST segun ID
    @PutMapping("/{postId}")
    public ResponseEntity<?> editPost(@PathVariable Long postId, @Valid @RequestBody Post post) {
        Post postEdit = postRepository.getOne(postId);
        postEdit.setTitle(post.getTitle());
        postEdit.setDescripcion(post.getDescripcion());
        postEdit.setContenido(post.getContenido());
        postEdit.setPublicado(post.getPublicado());
        
        return new ResponseEntity<>(postRepository.save(postEdit), HttpStatus.OK);
    }

    //DELETE Borrar un Post segun el ID
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        Post postDelete = postRepository.getOne(postId);
        postRepository.delete(postDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Map<Integer, String> inicializarRepo() {
        Map<Integer, String> posts = new HashMap<>();
        posts.put(1, "Java");
        posts.put(2, "React");
        return posts;
    }
}

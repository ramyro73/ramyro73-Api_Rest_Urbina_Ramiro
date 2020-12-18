package com.informatorio.myblog.controller;

import javax.validation.Valid;

import com.informatorio.myblog.model.Comment;
import com.informatorio.myblog.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Comment")

public class CommentController {

    @Autowired
    private CommentRepository commentRepository;



    //crear 
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.CREATED);
    }

    @GetMapping // ~ /api/v1/post
    public ResponseEntity<?> getComments() {
        return new ResponseEntity<>(commentRepository.findAll(), HttpStatus.OK);
    }

    //PUT para modificar 
    @PutMapping("/{commentId}")
    public ResponseEntity<?> editComment(@PathVariable Long commentId, @Valid @RequestBody Comment comment) {
        Comment commentEdit = commentRepository.getOne(commentId);
        commentEdit.setComentario(comment.getComentario());
        return new ResponseEntity<>(commentRepository.save(commentEdit), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        Comment commentDelete = commentRepository.getOne(commentId);
        commentRepository.delete(commentDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}

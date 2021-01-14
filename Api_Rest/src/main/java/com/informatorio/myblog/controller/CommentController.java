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
@RequestMapping("/api/v1/")

public class CommentController {

    @Autowired
    private CommentRepository commentRepository;



    //crear 
    @PostMapping("/Comment")
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.CREATED);
    }

    

    //PUT para modificar 
    @PutMapping("/Comment/{commentId}")
    public ResponseEntity<?> editComment(@PathVariable Long commentId, @Valid @RequestBody Comment comment) {
        Comment commentEdit = commentRepository.getOne(commentId);
        commentEdit.setComment(comment.getComment());
        return new ResponseEntity<>(commentRepository.save(commentEdit), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("/Comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        Comment commentDelete = commentRepository.getOne(commentId);
        commentRepository.delete(commentDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Todos los comments
    @GetMapping ("/Comment")
    public ResponseEntity<?> getComments() {
        return new ResponseEntity<>(commentRepository.findAll(), HttpStatus.OK);
    }

    
    
}
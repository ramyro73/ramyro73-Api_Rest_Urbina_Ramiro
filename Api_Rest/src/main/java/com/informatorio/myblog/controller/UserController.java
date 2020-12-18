package com.informatorio.myblog.controller;

import javax.validation.Valid;

import com.informatorio.myblog.model.User;
import com.informatorio.myblog.repository.UserRepository;

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
@RequestMapping("/api/v1/User")

public class UserController {
    @Autowired
    private UserRepository userRepository;
    //crear 
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    //PUT para modificar un POST segun ID
    @PutMapping("/{userId}")
    public ResponseEntity<?> editUser(@PathVariable Long userId, @Valid @RequestBody User user) {
        User userEdit = userRepository.getOne(userId);
        userEdit.setNombre(user.getNombre());
        userEdit.setApellido(user.getApellido());
        userEdit.setPassword(user.getPassword());
        userEdit.setCiudad(user.getCiudad());
        userEdit.setProvincia(user.getProvincia());
        userEdit.setPais(user.getPais());

        return new ResponseEntity<>(userRepository.save(userEdit), HttpStatus.OK);
    }

    //DELETE Borrar un Post segun el ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        User userDelete = userRepository.getOne(userId);
        userRepository.delete(userDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //GET Todos los POST
    @GetMapping // ~ /api/v1/post
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
}

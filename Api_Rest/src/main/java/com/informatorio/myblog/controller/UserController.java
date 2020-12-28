package com.informatorio.myblog.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.informatorio.myblog.model.Post;
import com.informatorio.myblog.model.User;
import com.informatorio.myblog.repository.PostRepository;
import com.informatorio.myblog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")

public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository; 

    /* @Autowired
    private CommentRepository commentRepository;  */
   
   
    //alta
    @PostMapping("/User")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }


    //Baja por id
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        User userDelete = userRepository.getOne(userId);
        userRepository.delete(userDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Modificacion 
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

    
    //consulta all
    @GetMapping("/User")
    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }


    
    
    //buscar por ciudad
    @GetMapping("/User/ciudad")
    public ResponseEntity<?> buscarUsuariosPorCiudad(@RequestParam String ciudad) {
        List<User> users = userRepository.findByCiudad(ciudad);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    
    //GET Todos los user creados despues de cierta fecha
    @GetMapping("/User/fecha")
    public ResponseEntity<?> buscarUsuariosCreadosDespuesDeFecha(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<User> users = userRepository.findByCreationDateAfter(date);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    @PostMapping("/User/{userId}/post")
    public ResponseEntity<?> createPostGivenUser(@PathVariable Long userId, @RequestBody Post post) {
    User user = userRepository.getOne(userId);
    user.addPost(post);
    return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
  }

  

    
}

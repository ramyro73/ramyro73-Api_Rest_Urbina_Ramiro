package com.informatorio.myblog.repository;

import java.util.List;

import com.informatorio.myblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTituloContains(String titulo);
    
    List<Post> findByPublicadoFalse();
    
}
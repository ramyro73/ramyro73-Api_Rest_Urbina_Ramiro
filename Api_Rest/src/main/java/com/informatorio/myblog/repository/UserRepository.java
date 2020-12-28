package com.informatorio.myblog.repository;

import java.time.LocalDate;
import java.util.List;

import com.informatorio.myblog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByCreationDateAfter(LocalDate date);

    List<User> findByCiudad(String ciudad);

}
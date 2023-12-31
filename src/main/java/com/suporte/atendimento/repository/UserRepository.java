package com.suporte.atendimento.repository;

import com.suporte.atendimento.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User findByUsername(String username);

    List<User> findAll();

    User findById(long id);

    User save(User user);

    void deleteById(long id);
}

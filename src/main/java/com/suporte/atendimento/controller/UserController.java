package com.suporte.atendimento.controller;

import com.suporte.atendimento.entity.AgenteAtendimento;
import com.suporte.atendimento.entity.User;
import com.suporte.atendimento.security.EmailValidator;
import com.suporte.atendimento.service.AgenteAtendimentoService;
import com.suporte.atendimento.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")

public class UserController {
    private final UserService userService;
    private final AgenteAtendimentoService agenteAtendimentoService;

    @Autowired
    public UserController(UserService userService, AgenteAtendimentoService agenteAtendimentoService) {
        this.userService = userService;
        this.agenteAtendimentoService = agenteAtendimentoService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (!EmailValidator.validate(user.getEmail())) {
            return ResponseEntity.badRequest().body("Endereço de e-mail inválido");
        }

        User createdUser = userService.createUser(user);

        // Criar Agente de Atendimento relacionado ao User criado
        AgenteAtendimento agenteAtendimento = new AgenteAtendimento();
        agenteAtendimento.setUser(createdUser);
        agenteAtendimentoService.createAgenteAtendimento(agenteAtendimento);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    @Secured("ROLE_USER")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            user.setId(id);
            User updatedUser = userService.updateUser(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
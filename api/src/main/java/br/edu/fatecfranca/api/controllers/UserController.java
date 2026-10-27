package br.edu.fatecfranca.api.controllers;

import br.edu.fatecfranca.api.dtos.UserResponse;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.fatecfranca.api.dtos.UserRequest;
import br.edu.fatecfranca.api.entities.User;
import br.edu.fatecfranca.api.services.UserService;
import jakarta.validation.Valid;


import br.edu.fatecfranca.api.dtos.LoginRequest;
import br.edu.fatecfranca.api.dtos.UserResponse;




// Define esta classe como um Controller REST
@RestController

// Define a rota principal dos usuários
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    // Construtor que recebe o UserService
    public UserController(UserService service) {
        this.service = service;
    }

      @PostMapping("/login")
   public ResponseEntity<UserResponse> login(
           @Valid @RequestBody LoginRequest request) {


       return service.authenticate(request)
               .map(UserResponse::new)
               .map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity
                       .status(HttpStatus.UNAUTHORIZED)
                       .build());
   }


    // ALTERADO: recebe UserRequest no cadastro
    // POST /users
    @PostMapping
   public ResponseEntity<UserResponse> create(
           @RequestBody UserRequest request) {


       User savedUser = service.create(request);


       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(new UserResponse(savedUser));
   }

    // GET /users
    // Lista todos os usuários cadastrados
   @GetMapping
   public List<UserResponse> findAll() {
       return service.findAll()
               .stream()
               .map(UserResponse::new)
               .toList();

    // GET /users/{id}
    // Busca um usuário pelo ID
    @GetMapping("/{id}")
   public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
       return service.findById(id)
               .map(UserResponse::new)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }


    // ALTERADO: recebe UserRequest para atualização
    // PUT /users/{id}
    @PutMapping("/{id}")
   public ResponseEntity<UserResponse> update(
           @PathVariable Long id,
           @RequestBody UserRequest request) {


       if (!service.existsById(id)) {
           return ResponseEntity.notFound().build();
       }


       User updatedUser = service.update(id, request);


       return ResponseEntity.ok(new UserResponse(updatedUser));
   }

    // DELETE /users/{id}
    // Exclui o usuário cadastrado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        service.deleteById(id);

        // Retorna HTTP 204 - No Content
        return ResponseEntity.noContent().build();
    }
}
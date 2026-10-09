package br.edu.fatecfranca.api.controllers;

import java.util.List;

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

import br.edu.fatecfranca.api.entities.User;
import br.edu.fatecfranca.api.services.UserService;

// Define esta classe como um Controller REST
@RestController

// Define a rota principal deste controller
// Todos os endpoints começam com /users
@RequestMapping("/users")
public class UserController {

    // Service utilizado pelo controller
    private final UserService service;

    // Injeção de dependência do UserService
    public UserController(UserService service) {
        this.service = service;
    }

    // POST /users
    // Responsável por cadastrar um novo usuário
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {

        // Envia o usuário recebido para a camada Service
        User savedUser = service.create(user);

        // Retorna HTTP 201 - Created
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }

    // GET /users
    // Retorna todos os usuários
    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }

    // GET /users/{id}
    // Procura um usuário específico pelo id
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {

        // Se encontrar o usuário, retorna HTTP 200
        // Se não encontrar, retorna HTTP 404
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT /users/{id}
    // Atualiza um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable Long id,
            @RequestBody User user) {

        // Primeiro verifica se o usuário existe
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Define o id recebido pela URL no objeto
        // para garantir que o usuário correto seja atualizado
        user.setId(id);

        // Atualiza o usuário e retorna HTTP 200
        return ResponseEntity.ok(service.update(user));
    }

    // DELETE /users/{id}
    // Exclui um usuário pelo id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        // Verifica se o usuário existe antes de excluir
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Exclui o usuário
        service.deleteById(id);

        // Retorna HTTP 204 - No Content
        return ResponseEntity.noContent().build();
    }
}
package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.fatecfranca.api.dtos.UserRequest;
import br.edu.fatecfranca.api.entities.User;
import br.edu.fatecfranca.api.repositories.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import br.edu.fatecfranca.api.dtos.LoginRequest;



@Service
public class UserService {

    private final UserRepository repository;
     private final PasswordEncoder passwordEncoder;
    // Construtor que recebe o repository
      public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
       this.repository = repository;
       this.passwordEncoder = passwordEncoder;
   }



    // NOVO: copia os dados do DTO para a entidade User
    // Incrementado em 23/10/2026
     private void copyToEntity(UserRequest request, User user) {
       user.setFullname(request.fullname());
       user.setUsername(request.username());
       user.setEmail(request.email());
       // passwordEncoder gera o hash para a senha recebida
       user.setPassword(passwordEncoder.encode(request.password()));
       user.setIsAdmin(Boolean.TRUE.equals(request.isAdmin()));
   }


      public Optional<User> authenticate(LoginRequest request) {
       String usernameOrEmail = request.usernameOrEmail().strip();


       Optional<User> result =
               repository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);


       if (result.isEmpty()) {
           return Optional.empty();
       }


       User user = result.get();


       // Neste ponto ocorre a comparação entre os hashes da senha
       // digitada e da senha cadastrada
       boolean validPassword = passwordEncoder.matches(
           request.password(),
           user.getPassword()
       );


       if (!validPassword) {
           return Optional.empty();
       }


       return Optional.of(user);
   }


    // ALTERADO: agora recebe UserRequest em vez de User
    public User create(UserRequest request) {

        // Cria um novo objeto User
        User user = new User();

        // Transfere os dados do DTO para a entidade
        copyToEntity(request, user);

        // Salva o usuário no banco
        return repository.save(user);
    }

    // Lista todos os usuários
    public List<User> findAll() {
        return repository.findAll();
    }

    // Busca usuário pelo ID
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    // Verifica se existe um usuário com o ID informado
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    // ALTERADO: recebe o ID e os dados do DTO
    public User update(Long id, UserRequest request) {

        // Busca o usuário existente no banco
        User user = repository.findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException(
                        "Usuário não encontrado: " + id
                    )
                );

        // Atualiza os atributos com os dados do DTO
        copyToEntity(request, user);

        // Salva as alterações
        return repository.save(user);
    }

    // Exclui um usuário pelo ID
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
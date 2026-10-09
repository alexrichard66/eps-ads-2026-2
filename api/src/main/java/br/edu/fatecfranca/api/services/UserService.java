package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.fatecfranca.api.entities.User;
import br.edu.fatecfranca.api.repositories.UserRepository;

// Indica que esta classe é uma camada de serviço do Spring
// A Service fica entre o Controller e o Repository
@Service
public class UserService {

    // Repository utilizado para acessar os dados dos usuários
    private final UserRepository repository;

    // Injeção de dependência do UserRepository
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    // Cria um novo usuário no banco
    // O método save() faz o INSERT quando o usuário ainda não possui id
    public User create(User user) {
        return repository.save(user);
    }

    // Retorna todos os usuários cadastrados
    public List<User> findAll() {
        return repository.findAll();
    }

    // Procura um usuário pelo id
    // Optional é utilizado porque o usuário pode ou não existir
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    // Verifica se existe um usuário com determinado id
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    // Atualiza um usuário existente
    // O save() também pode fazer UPDATE quando o objeto já possui id
    public User update(User user) {
        return repository.save(user);
    }

    // Remove um usuário pelo id
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
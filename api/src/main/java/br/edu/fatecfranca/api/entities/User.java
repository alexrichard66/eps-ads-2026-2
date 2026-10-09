package br.edu.fatecfranca.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Define que esta classe representa uma entidade do banco de dados
@Entity

// Informa que esta entidade está ligada à tabela "users"
@Table(name = "users")
public class User {

    // Define o campo id como chave primária
    @Id

    // Define que o valor do id será gerado automaticamente pelo banco
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Campo fullname da tabela users
    // nullable = false significa que ele é obrigatório
    @Column(nullable = false)
    private String fullname;

    // Campo username da tabela users
    // unique = true impede usernames repetidos
    @Column(nullable = false, unique = true)
    private String username;

    // Campo email da tabela users
    // unique = true impede emails repetidos
    @Column(nullable = false, unique = true)
    private String email;

    // Campo responsável por armazenar a senha do usuário
    @Column(nullable = false)
    private String password;

    // Mapeia o atributo isAdmin para a coluna is_admin do banco
    // O valor padrão é false
    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin = false;

    // Construtor vazio obrigatório para o JPA
    public User() {
    }

    // Getter do id
    public Long getId() {
        return id;
    }

    // Setter do id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter do nome completo
    public String getFullname() {
        return fullname;
    }

    // Setter do nome completo
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    // Getter do username
    public String getUsername() {
        return username;
    }

    // Setter do username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter do email
    public String getEmail() {
        return email;
    }

    // Setter do email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter da senha
    public String getPassword() {
        return password;
    }

    // Setter da senha
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter que informa se o usuário é administrador
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    // Setter que define se o usuário é administrador
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
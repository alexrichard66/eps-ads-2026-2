package br.edu.fatecfranca.api.dtos;

// DTO criado para representar os dados recebidos
// em uma requisição relacionada a User
//
// Nesta prova, conforme solicitado,
// NÃO é necessário utilizar este DTO
// dentro do Service e do Controller
public record UserRequest(

    // Nome completo do usuário
    String fullname,

    // Nome de usuário
    String username,

    // Email do usuário
    String email,

    // Senha do usuário
    String password,

    // Define se o usuário é administrador
    Boolean isAdmin

) {
}
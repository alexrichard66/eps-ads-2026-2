
package br.edu.fatecfranca.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// DTO responsável por receber e validar
// os dados enviados para cadastrar ou atualizar usuários.
public record UserRequest(

    // Nome completo obrigatório.
    @NotBlank(message = "O nome completo é obrigatório.")
    String fullname,

    // Nome de usuário obrigatório.
    // Aceita letras sem acento, números,
    // ponto, hífen e sublinhado.
    @NotBlank(message = "O nome de usuário é obrigatório.")
    @Pattern(
        regexp = "^[a-zA-Z0-9._-]+$",
        message = "O usuário deve conter apenas letras sem acentos, números, ponto, hífen e sublinhado."
    )
    String username,

    // E-mail obrigatório e com formato válido.
    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail deve ter um formato válido.")
    String email,

    // Senha obrigatória com pelo menos 8 caracteres.
    @NotBlank(message = "A senha é obrigatória.")
    @Size(
        min = 8,
        message = "A senha deve conter pelo menos 8 caracteres."
    )
    String password,

    // Deve informar true ou false.
    @NotNull(message = "Informe se o usuário é administrador.")
    Boolean isAdmin

) {}

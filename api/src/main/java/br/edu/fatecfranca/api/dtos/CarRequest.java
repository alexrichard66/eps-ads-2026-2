
package br.edu.fatecfranca.api.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Digits;

// DTO responsável por receber e validar
// os dados enviados para cadastrar ou atualizar um carro.
public record CarRequest(

    // Marca obrigatória, não pode ser nula ou vazia.
    @NotBlank(message = "A marca é obrigatória.")
    String brand,

    // Modelo obrigatório.
    @NotBlank(message = "O modelo é obrigatório.")
    String model,

    // Cor obrigatória.
    @NotBlank(message = "A cor é obrigatória.")
    String color,

    // Ano obrigatório e maior que zero.
    @NotNull(message = "O ano de fabricação é obrigatório.")
    @Positive(message = "O ano de fabricação deve ser maior que zero.")
    Integer yearManufacture,

    // Deve informar true ou false.
    @NotNull(message = "Informe se o carro é importado.")
    Boolean imported,

    // Placa obrigatória.
    // Aceita formato antigo ABC-1234 ou Mercosul ABC1D23.
    @NotBlank(message = "A placa é obrigatória.")
    @Pattern(
        regexp = "^([A-Z]{3}-[0-9]{4}|[A-Z]{3}[0-9][A-Z][0-9]{2})$",
        message = "A placa deve estar no formato ABC-1234 ou ABC1D23."
    )
    String plates,

    // Data opcional, mas não pode ser futura.
    @PastOrPresent(
        message = "A data da venda não pode estar no futuro."
    )
    LocalDate sellingDate,

    // Preço opcional, mas quando informado:
    // deve ser positivo e ter até 10 dígitos inteiros
    // e no máximo 2 casas decimais.
    @Positive(message = "O preço de venda deve ser maior que zero.")
    @Digits(
        integer = 10,
        fraction = 2,
        message = "O preço deve ter até 10 dígitos inteiros e 2 decimais."
    )
    BigDecimal sellingPrice,

    // Cliente opcional, mas seu ID deve ser positivo.
    @Positive(message = "O ID do cliente deve ser maior que zero.")
    Long customerId

) {}

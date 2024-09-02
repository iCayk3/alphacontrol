package br.com.w4solution.alphacontrol.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;

public record UsuarioDados(
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotBlank
        String rg,
        @NotBlank
        String nascimento,
        String rua,
        Integer numero,
        String bairro,
        String  cidade,
        String uf,
        String cep,
        String complemento,
        String telefone,
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}

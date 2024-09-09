package br.com.w4solution.alphacontrol.dto.usuario;

import jakarta.validation.constraints.NotBlank;

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

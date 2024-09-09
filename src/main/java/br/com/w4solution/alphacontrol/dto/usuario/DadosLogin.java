package br.com.w4solution.alphacontrol.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosLogin(@NotBlank String email, @NotBlank String password) {
}

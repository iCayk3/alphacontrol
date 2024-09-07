package br.com.w4solution.alphacontrol.dto.usuario;

import org.hibernate.validator.constraints.NotBlank;

public record DadosLogin(@NotBlank String email, @NotBlank String password) {
}

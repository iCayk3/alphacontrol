package br.com.w4solution.alphacontrol.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record RecuperarSenhaDTO(@NotBlank String senhaNova) {
}

package br.com.w4solution.alphacontrol.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record AtualizarSenhaDTO(@NotBlank String senhaAntiga, @NotBlank String senhaNova) {
}

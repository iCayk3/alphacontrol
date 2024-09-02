package br.com.w4solution.alphacontrol.dto;

import br.com.w4solution.alphacontrol.model.financeiro.TipoEntrada;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroTransacaoDTO(
        @NotNull Double valor,
        @NotBlank TipoEntrada tipoEntrada
        ) {
}

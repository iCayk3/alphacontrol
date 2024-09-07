package br.com.w4solution.alphacontrol.dto.usuario;

import br.com.w4solution.alphacontrol.model.pessoa.Pessoa;

public record DetalhesUsuario(
        Long id,
        String nome,
        String usuario
) {
    public DetalhesUsuario(Pessoa dados){
        this(dados.getId(), dados.getNome(), dados.getUsuario().getUsuario());
    }
}

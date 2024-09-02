package br.com.w4solution.alphacontrol.dto;

import br.com.w4solution.alphacontrol.model.pessoa.Usuario;
import br.com.w4solution.alphacontrol.model.pessoa.Pessoa;

public record DetalhesUsuario(
        Long id,
        String nome,
        Usuario usuario
) {
    public DetalhesUsuario(Pessoa dados){
        this(dados.getId(), dados.getNome(), dados.getUsuario());
    }
}

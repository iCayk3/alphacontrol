package br.com.w4solution.alphacontrol.dto;

import br.com.w4solution.alphacontrol.model.financeiro.TipoEntrada;
import br.com.w4solution.alphacontrol.model.financeiro.TransacaoFinanceira;
import br.com.w4solution.alphacontrol.model.pessoa.Usuario;

import java.time.LocalDateTime;

public record DetalhesTransacao(Long id, Double valor, String usuario, TipoEntrada entrada, LocalDateTime datahora) {
    public DetalhesTransacao(TransacaoFinanceira transacao){
        this(transacao.getId(), transacao.getValor(), transacao.getUsuario().getUsuario(), transacao.getTipoEntrada(), transacao.getDataHoraRegistro());
    }
}

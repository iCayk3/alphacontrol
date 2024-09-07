package br.com.w4solution.alphacontrol.dto.transacao;

import br.com.w4solution.alphacontrol.model.financeiro.TipoEntrada;
import br.com.w4solution.alphacontrol.model.financeiro.TransacaoFinanceira;

public record DadosListagemTransacoes(Long id, String usurio, Double valor, TipoEntrada tipoEntrada) {
    public DadosListagemTransacoes(TransacaoFinanceira dados) {
        this(dados.getId(), dados.getUsuario().getUsuario(), dados.getValor(), dados.getTipoEntrada());
    }
}

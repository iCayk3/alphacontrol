package br.com.w4solution.alphacontrol.controller;

import br.com.w4solution.alphacontrol.dto.CadastroTransacaoDTO;
import br.com.w4solution.alphacontrol.dto.DadosListagemTransacoes;
import br.com.w4solution.alphacontrol.dto.DetalhesTransacao;
import br.com.w4solution.alphacontrol.infra.security.TokenSerivceApi;
import br.com.w4solution.alphacontrol.model.financeiro.TransacaoFinanceira;
import br.com.w4solution.alphacontrol.model.pessoa.Usuario;
import br.com.w4solution.alphacontrol.repository.TransacaoRepository;
import br.com.w4solution.alphacontrol.repository.UsuaioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("transacao")
public class TransacaoController {

    @Autowired
    TokenSerivceApi tokenSerivceApi;
    @Autowired
    UsuaioRepository repository;
    @Autowired
    TransacaoRepository transacaoRepository;

    @GetMapping
    public ResponseEntity<Page<DadosListagemTransacoes>> listarTransacoes(@PageableDefault(size = 10) Pageable paginacao){
        var page = transacaoRepository.findAll(paginacao).map(DadosListagemTransacoes::new);
        return ResponseEntity.ok(page);
    }


    @PostMapping("cadastro")
    @Transactional
    public ResponseEntity cadastrarTransacao(@RequestHeader(value = "Authorization") String token, @RequestBody CadastroTransacaoDTO dados, UriComponentsBuilder uriBuilder){
        var usuarioRequisica = tokenSerivceApi.getSubject(token.replace("Bearer ", ""));
        var usuario = repository.findByUsuario(usuarioRequisica);

        var transacaoFinanceira = new TransacaoFinanceira(dados, (Usuario) usuario);
        transacaoRepository.save(transacaoFinanceira);

        var uri = uriBuilder.path("/transacao/{id}").buildAndExpand(transacaoFinanceira.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalhesTransacao(transacaoFinanceira));
    }
}

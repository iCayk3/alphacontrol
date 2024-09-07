package br.com.w4solution.alphacontrol.controller;

import br.com.w4solution.alphacontrol.dto.services.TokenDTO;
import br.com.w4solution.alphacontrol.dto.usuario.*;
import br.com.w4solution.alphacontrol.infra.security.TokenSerivceApi;
import br.com.w4solution.alphacontrol.model.pessoa.Pessoa;
import br.com.w4solution.alphacontrol.model.pessoa.Usuario;
import br.com.w4solution.alphacontrol.repository.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    AuthenticationManager manager;
    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    BCryptPasswordEncoder encoderSenha;
    @Autowired
    private TokenSerivceApi tokenSerivceApi;

    @GetMapping
    public ResponseEntity<Page<DetalhesPessoa>> listarAtivos(@PageableDefault(size = 10) Pageable paginacao){
        var page = pessoaRepository.findAllByAtivoTrue(paginacao).map(DetalhesPessoa::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity listarPorId(@PathVariable Long id){
        var usuario = pessoaRepository.findById(id).get();
        return ResponseEntity.ok(new DetalhesUsuario(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosLogin dados){

        var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokenSerivceApi.gerarToken((Usuario)authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }

    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid UsuarioDados dados, UriComponentsBuilder uriBuilder){
        var usuario = new Pessoa(dados, encoderSenha);
        pessoaRepository.save(usuario);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUsuario(usuario));
    }
    @PutMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity atualizarSenha(@PathVariable Long id, @RequestBody @Valid AtualizarSenhaDTO dados){
        var pessoa = pessoaRepository.findById(id).get().getUsuario();
        pessoa.atualizarSenha(dados.senhaAntiga(), dados.senhaNova(), encoderSenha);
        return ResponseEntity.ok("Senha atualizada!");
    }

    @PutMapping("/recuperarsenha/{id}")
    @Transactional
    public ResponseEntity recuperarSenha(@PathVariable Long id, @RequestBody @Valid RecuperarSenhaDTO dados){
        var pessoa = pessoaRepository.findById(id).get().getUsuario();
        pessoa.recuperarSenha(dados.senhaNova(), encoderSenha);
        return ResponseEntity.ok("Senha recuperada!");
    }

    @PutMapping("/ativar/{id}")
    @Transactional
    public ResponseEntity ativarUsuario(@PathVariable Long id){
        var usuario = pessoaRepository.findById(id);
        usuario.get().ativacaoLogica();
        return ResponseEntity.ok(new DetalhesPessoa(usuario.get()));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity excluirUsuario(@PathVariable Long id){
        var usuario = pessoaRepository.findById(id);
        usuario.get().exclusaoLogica();
        return ResponseEntity.ok(new DetalhesPessoa(usuario.get()));
    }

}

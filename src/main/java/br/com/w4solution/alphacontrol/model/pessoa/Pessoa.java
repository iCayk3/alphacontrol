package br.com.w4solution.alphacontrol.model.pessoa;

import br.com.w4solution.alphacontrol.dto.usuario.UsuarioDados;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity(name = "Pessoa")
@Table(name = "pessoas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String cpf;
    private String rg;
    private String nascimento;
    @Embedded
    private Endereco endereco;
    @Embedded
    private Contato contato;
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;
    private Boolean ativo = true;

    public Pessoa(UsuarioDados dados, BCryptPasswordEncoder encoderSenha){
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.rg = dados.rg();
        this.nascimento = dados.nascimento();
        this.endereco = new Endereco(dados);
        this.contato = new Contato(dados);
        this.usuario = new Usuario(dados, encoderSenha);
    }

    public void exclusaoLogica(){
        this.ativo = false;
    }
    public void ativacaoLogica(){
        this.ativo = true;
    }
}

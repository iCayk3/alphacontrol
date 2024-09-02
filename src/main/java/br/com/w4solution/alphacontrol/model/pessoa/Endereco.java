package br.com.w4solution.alphacontrol.model.pessoa;

import br.com.w4solution.alphacontrol.dto.UsuarioDados;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Endereco {
    private String rua;
    private Integer numero;
    private String bairro;
    private String  cidade;
    private String uf;
    private String cep;
    private String complemento;

    public Endereco(UsuarioDados dados) {
        this.rua = dados.rua();
        this.numero = dados.numero();
        this.bairro = dados.bairro();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.cep = dados.cep();
        this.complemento = dados.complemento();
    }
}

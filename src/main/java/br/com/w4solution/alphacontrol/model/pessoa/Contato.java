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
public class Contato {
    private String telefone;
    private String email;

    public Contato(UsuarioDados dados) {
        this.telefone = dados.telefone();
        this.email = dados.email();
    }
}

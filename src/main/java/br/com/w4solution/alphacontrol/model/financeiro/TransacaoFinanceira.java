package br.com.w4solution.alphacontrol.model.financeiro;

import br.com.w4solution.alphacontrol.dto.transacao.CadastroTransacaoDTO;
import br.com.w4solution.alphacontrol.model.pessoa.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity(name = "TransacaoFinanceira")
@Table(name = "transacoesFinanceiras")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TransacaoFinanceira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat
    private LocalDateTime dataHoraRegistro;
    private Double valor;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuario;
    @Enumerated(EnumType.STRING)
    private TipoEntrada tipoEntrada;


    public TransacaoFinanceira(CadastroTransacaoDTO dados, Usuario usuario){
        this.dataHoraRegistro = LocalDateTime.now();
        this.valor = dados.valor();
        this.usuario = usuario;
        this.tipoEntrada = dados.tipoEntrada();
    }
}



package br.com.w4solution.alphacontrol.dto.usuario;

import br.com.w4solution.alphacontrol.model.pessoa.Pessoa;
import org.hibernate.validator.constraints.NotBlank;

public record DetalhesPessoa(
        String nome,
        String cpf,
        String rg,
        String nascimento,
        String rua,
        Integer numero,
        String bairro,
        String  cidade,
        String uf,
        String cep,
        String complemento,
        String telefone,
        @NotBlank
        String email,
        Boolean ativo
) {
        public DetalhesPessoa(Pessoa dados){
                this(dados.getNome(), dados.getCpf(), dados.getRg(), dados.getNascimento(),dados.getEndereco().getRua()
                ,dados.getEndereco().getNumero(), dados.getEndereco().getBairro(), dados.getEndereco().getCidade(),
                        dados.getEndereco().getUf(), dados.getEndereco().getCep(), dados.getEndereco().getComplemento(),
                        dados.getContato().getTelefone(), dados.getContato().getEmail(),
                        dados.getAtivo());
        }
}

package br.com.w4solution.alphacontrol.model.pessoa;

import br.com.w4solution.alphacontrol.dto.usuario.UsuarioDados;
import br.com.w4solution.alphacontrol.model.financeiro.TransacaoFinanceira;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String usuario;
    private String senha;
    @OneToOne(mappedBy = "usuario")
    private Pessoa pessoa;
    @OneToMany(mappedBy = "usuario")
    private List<TransacaoFinanceira> transacaoFinanceira;


    public Usuario(UsuarioDados dados, BCryptPasswordEncoder encoderSenha){
        this.usuario = dados.email();
        this.senha = encoderSenha.encode(dados.password());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void atualizarSenha(String senhaAntiga, String senhaNova, BCryptPasswordEncoder encode){
        if(encode.matches(senhaAntiga, this.senha)){
            this.senha = encode.encode(senhaNova);
        }else {
            throw new RuntimeException("Senha antiga incorreta!");
        }
    }

    public void recuperarSenha(String novaSenha, BCryptPasswordEncoder encoderSenha){
        this.senha = encoderSenha.encode(novaSenha);
    }
}

package br.com.w4solution.alphacontrol.infra.security;

import br.com.w4solution.alphacontrol.model.pessoa.Usuario;
import br.com.w4solution.alphacontrol.repository.UsuaioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenSerivceApi tokenSerivceApi;
    @Autowired
    UsuaioRepository usuaioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        filterChain.doFilter(request, response);
    }

    public static String recuperarToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if(token == null){
            throw new RuntimeException("Token JWT nao enviado no cabeçalho Authorization");
        }
        return token.replace("Bearer ", "");
    }
}

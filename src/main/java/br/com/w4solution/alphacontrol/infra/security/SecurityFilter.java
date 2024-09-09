package br.com.w4solution.alphacontrol.infra.security;

import br.com.w4solution.alphacontrol.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenSerivceApi tokenSerivceApi;
    @Autowired
    UsuarioRepository usuaioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);
        if(tokenJWT != null){
            var subject = tokenSerivceApi.getSubject(tokenJWT);
            var usuario =  usuaioRepository.findByUsuario(subject);
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    public static String recuperarToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if(token != null){
            return token.replace("Bearer ", "").trim();
        }
        return null;
    }
}

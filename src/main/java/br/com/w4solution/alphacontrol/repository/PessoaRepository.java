package br.com.w4solution.alphacontrol.repository;

import br.com.w4solution.alphacontrol.dto.DetalhesPessoa;
import br.com.w4solution.alphacontrol.model.pessoa.Pessoa;
import ch.qos.logback.core.testUtil.MockInitialContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findAllByAtivoTrue();

    Page<Pessoa> findAllByAtivoTrue(Pageable paginacao);
}

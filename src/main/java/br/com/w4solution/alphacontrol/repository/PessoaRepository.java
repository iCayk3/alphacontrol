package br.com.w4solution.alphacontrol.repository;

import br.com.w4solution.alphacontrol.model.pessoa.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Page<Pessoa> findAllByAtivoTrue(Pageable paginacao);
}

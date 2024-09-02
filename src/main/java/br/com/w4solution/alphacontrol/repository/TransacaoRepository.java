package br.com.w4solution.alphacontrol.repository;

import br.com.w4solution.alphacontrol.model.financeiro.TransacaoFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<TransacaoFinanceira, Long> {
}

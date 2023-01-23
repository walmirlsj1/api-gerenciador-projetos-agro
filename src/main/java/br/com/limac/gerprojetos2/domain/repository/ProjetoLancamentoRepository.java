package br.com.limac.gerprojetos2.domain.repository;

import br.com.limac.gerprojetos2.domain.model.ProjetoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoLancamentoRepository extends JpaRepository<ProjetoLancamento, Long> {
}

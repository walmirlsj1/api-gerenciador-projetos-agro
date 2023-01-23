package br.com.limac.gerprojetos2.domain.repository;

import br.com.limac.gerprojetos2.domain.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}

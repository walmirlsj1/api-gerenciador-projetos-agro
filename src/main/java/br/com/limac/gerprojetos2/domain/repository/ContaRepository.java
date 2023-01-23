package br.com.limac.gerprojetos2.domain.repository;

import br.com.limac.gerprojetos2.domain.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}

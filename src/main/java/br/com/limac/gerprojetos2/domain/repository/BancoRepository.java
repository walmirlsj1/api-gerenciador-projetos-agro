package br.com.limac.gerprojetos2.domain.repository;

import br.com.limac.gerprojetos2.domain.model.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<Banco, Long> {

}

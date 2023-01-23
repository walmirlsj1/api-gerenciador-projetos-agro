package br.com.limac.gerprojetos2.domain.repository;

import br.com.limac.gerprojetos2.domain.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findByNomeContaining(String nome, Pageable pageable);

}

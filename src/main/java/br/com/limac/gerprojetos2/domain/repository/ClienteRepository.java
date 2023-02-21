package br.com.limac.gerprojetos2.domain.repository;

import br.com.limac.gerprojetos2.domain.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
//    Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    List<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);


}

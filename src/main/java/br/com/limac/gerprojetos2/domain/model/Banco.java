package br.com.limac.gerprojetos2.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_BANCO")
@SequenceGenerator(name ="banco_seq", sequenceName = "banco_seq", initialValue = 1, allocationSize = 1)
public class Banco {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "banco_seq")
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 120)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private Long agencia;

    @OneToMany
    @JsonIgnore
    private List<Conta> contas = new ArrayList<>();

    @Column(columnDefinition = "boolean default true")
    private boolean estaAtivo;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataAtualizacao;

    @Version
    @Column(name = "opt_lock" )
    private Long version;
}

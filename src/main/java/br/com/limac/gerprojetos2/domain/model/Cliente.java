package br.com.limac.gerprojetos2.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_CLIENTE")
@SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq", initialValue = 1, allocationSize = 1)
public class Cliente {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    private Long id;

    @NotBlank(message = "Campo nome nao pode esta vazio")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "fantasia")
    private String fantasia;

    @NotBlank(message = "Campo CPF/CNPJ nao pode esta vazio")
    @Column(name = "cpf_cnpj", unique = true)
    private String cpfCnpj;

    @Column(name = "rg_ie", unique = true)
    private String rgIe;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "celular")
    private String celular;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_empresa", nullable = false)
    private ETipoPessoa tipoEmpresa;


    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Projeto> projetos;

    @Column(columnDefinition = "boolean default true")
    private boolean estaAtivo = true;

    //    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dataCriacao;

    //    @UpdateTimestamp
    private OffsetDateTime dataAtualizacao;

    @Version
    @Column(name = "opt_lock")
    private Long version;


    @PrePersist
    public void onInsert() {
        this.dataCriacao = OffsetDateTime.now();
        this.dataAtualizacao = this.dataCriacao;
    }

    @PreUpdate
    public void onUpdate() {
        this.dataAtualizacao = OffsetDateTime.now();
    }
}

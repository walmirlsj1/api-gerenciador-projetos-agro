package br.com.limac.gerprojetos2.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.List;
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_CLIENTE")
@SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq", initialValue = 1, allocationSize = 1)
public class Cliente
{
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
    @Column(name = "cpf_npj")
    private String cpfCnpj;

    @Column(name = "rg_ie")
    private String rgIe;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "celular")
    private String celular;

    @Column(name = "tipo_empresa", columnDefinition = "boolean default false", nullable = false)
    private Boolean tipoEmpresa = false;


    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Projeto> projetos;

    @Column(columnDefinition = "boolean default true")
    private boolean estaAtivo;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataAtualizacao;

    @Version
    @Column(name = "opt_lock")
    private Long version;
}

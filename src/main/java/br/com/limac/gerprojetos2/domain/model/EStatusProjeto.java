package br.com.limac.gerprojetos2.domain.model;

import java.util.Arrays;
import java.util.List;

public enum EStatusProjeto {

    PENDENTE("Pendente"),

    INICIADO("Iniciado", PENDENTE),

    EXECUCAO("Execução", INICIADO),

    FINALIZADO("Finalizado", EXECUCAO),

    CANCELADO("Cancelado", PENDENTE);

    private String descricao;
    private List<EStatusProjeto> statusAnteriores;

    EStatusProjeto(String descricao, EStatusProjeto... statusAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = Arrays.asList(statusAnteriores);
    }

    public String getDescricao() {
        return this.descricao;
    }

    public boolean naoPodeAlterarPara(EStatusProjeto novoStatus) {
        return !novoStatus.statusAnteriores.contains(this);
    }

    public boolean podeAlterarPara(EStatusProjeto novoStatus) {
        return !naoPodeAlterarPara(novoStatus);
    }
}

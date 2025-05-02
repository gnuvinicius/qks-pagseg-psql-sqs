package dev.garage474.domain.venda;

import lombok.Getter;

@Getter
public enum EnumStatus {
    PENDENTE(0, "Pendente"),
    EM_ANDAMENTO(1, "Em Andamento"),
    FINALIZADO(2, "Finalizado"),
    CANCELADO(3, "Cancelado");

    private int codigo;
    private String descricao;

    EnumStatus(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
}
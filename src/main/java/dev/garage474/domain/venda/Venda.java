package dev.garage474.domain.venda;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dev.garage474.domain.cadastro.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Venda extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "valor_total")
    private double valorTotal;

    @Column(name = "data_venda")
    private LocalDateTime dataVenda;

    @Column(name = "forma_pagamento")
    private EnumFormaPagamento formaPagamento;
    private EnumStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private final List<ItemVenda> itensVenda = new ArrayList<>();

    @Column(name = "num_transacao")
    private String numTransacao;

    public Venda(Cliente cliente, EnumFormaPagamento formaPagamento) {
        this.cliente = cliente;
        this.dataVenda = LocalDateTime.now();
        this.formaPagamento = formaPagamento;
        this.status = EnumStatus.EM_ANDAMENTO;
    }

    public void addItem(String produtoId, int quantidade) {
        ItemVenda itemVenda = new ItemVenda(this, produtoId, quantidade);
        itemVenda.persist();

        this.itensVenda.add(itemVenda);
        this.valorTotal += Objects.requireNonNull(this.itensVenda.stream()
                        .reduce(null, (a, b) -> a == null ? b : a))
                .getPrecoTotal();
    }

}

package dev.garage474.domain.venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dev.garage474.domain.cadastro.Cliente;
import dev.garage474.domain.cadastro.Produto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Venda extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_seq")
    @SequenceGenerator(name = "venda_seq", sequenceName = "venda_seq", allocationSize = 1, initialValue = 1)
    private int id;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

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
        this.valorTotal = BigDecimal.ZERO;
        this.status = EnumStatus.EM_ANDAMENTO;
    }

    public void addItem(Produto produto, int quantidade) {
        var item = new ItemVenda(this, produto, quantidade);
        item.persist();
        this.itensVenda.add(item);
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void atualizaStatus(EnumStatus status) {
        this.status = status;
    }

}

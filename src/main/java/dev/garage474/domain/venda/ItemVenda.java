package dev.garage474.domain.venda;

import dev.garage474.domain.cadastro.Produto;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "item_venda")
public class ItemVenda extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_venda_seq")
  @SequenceGenerator(name = "item_venda_seq", sequenceName = "item_venda_seq", allocationSize = 1)
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "produto_id")
  private Produto produto;

  private int quantidade;

  @Column(name = "preco_unitario")
  private double precoUnitario;

  @Column(name = "preco_total")
  private double precoTotal;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "venda_id")
  private Venda venda;

  public ItemVenda(Venda venda, String produtoId, int quantidade) {
    this.venda = venda;
    this.produto = Produto.findByIdOptional(produtoId)
        .map(Produto.class::cast)
        .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
    this.quantidade = quantidade;
    this.precoUnitario = produto.getPreco();
    this.precoTotal = precoUnitario * quantidade;
  }

}

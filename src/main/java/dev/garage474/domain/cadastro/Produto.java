package dev.garage474.domain.cadastro;

import java.math.BigDecimal;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Produto extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
  @SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", allocationSize = 1, initialValue = 1)
  private int id;
  private String nome;
  private String descricao;
  private BigDecimal preco;
  private int quantidade;

}

package dev.garage474.domain.cadastro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Produto extends PanacheEntityBase {

  @Id
  @GeneratedValue
  private int id;
  private String nome;
  private String descricao;
  private double preco;
  private int quantidade;

}

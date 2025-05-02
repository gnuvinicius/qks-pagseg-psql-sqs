package dev.garage474.domain.cadastro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Cliente extends PanacheEntityBase {

  @Id
  @GeneratedValue
  private int id;
  private String nome;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "endereco_id")
  private Endereco endereco;
  private String telefone;
  private String email;

  public static Cliente getClienteById(Integer id) {
    return Cliente.findByIdOptional(id)
            .map(Cliente.class::cast)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
  }
}

package dev.garage474.domain.cadastro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Cliente extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
  @SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq", allocationSize = 1, initialValue = 1)
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

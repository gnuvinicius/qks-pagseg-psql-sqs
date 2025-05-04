package dev.garage474.domain.cadastro;

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
public class Endereco extends PanacheEntityBase {
  
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_seq")
  @SequenceGenerator(name = "endereco_seq", sequenceName = "endereco_seq", allocationSize = 1, initialValue = 1)
  private int id;
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cidade;
  private String estado;
  private String cep;

}

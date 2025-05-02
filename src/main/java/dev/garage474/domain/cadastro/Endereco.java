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
public class Endereco extends PanacheEntityBase {
  
  @Id
  @GeneratedValue
  private int id;
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cidade;
  private String estado;
  private String cep;

}

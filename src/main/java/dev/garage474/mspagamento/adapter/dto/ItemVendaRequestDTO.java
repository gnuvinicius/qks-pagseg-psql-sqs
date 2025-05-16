package dev.garage474.mspagamento.adapter.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemVendaRequestDTO {
  private Integer produtoId;
  private int quantidade;


}

package dev.garage474.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemVendaRequestDTO {
  private String produtoId;
  private int quantidade;


}

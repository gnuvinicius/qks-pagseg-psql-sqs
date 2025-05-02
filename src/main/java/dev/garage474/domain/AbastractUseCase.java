package dev.garage474.domain;

import dev.garage474.application.dto.BaseDTO;

public abstract class AbastractUseCase<DTO extends BaseDTO> {
  
  protected abstract void executa();

//  protected abstract DTO executa(DTO dto);
}

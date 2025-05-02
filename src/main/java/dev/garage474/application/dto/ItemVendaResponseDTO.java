package dev.garage474.application.dto;

import dev.garage474.domain.venda.ItemVenda;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemVendaResponseDTO {
    private ProdutoDTO produto;
    private Integer quantidade;

    public static ItemVendaResponseDTO fromEntity(ItemVenda item) {
        return ItemVendaResponseDTO.builder()
                .produto(ProdutoDTO.fromEntity(item.getProduto()))
                .build();
    }

}

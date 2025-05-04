package dev.garage474.application.dto;

import java.math.BigDecimal;

import dev.garage474.domain.venda.ItemVenda;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemVendaResponseDTO {
    private ProdutoDTO produto;
    private Integer quantidade;
    private BigDecimal precoTotal;

    public static ItemVendaResponseDTO fromEntity(ItemVenda item) {
        return ItemVendaResponseDTO.builder()
                .quantidade(item.getQuantidade())
                .precoTotal(item.getPrecoTotal())
                .produto(ProdutoDTO.fromEntity(item.getProduto()))
                .build();
    }

}

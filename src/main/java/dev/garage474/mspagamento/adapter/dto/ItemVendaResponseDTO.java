package dev.garage474.mspagamento.adapter.dto;

import java.math.BigDecimal;
import java.util.List;

import dev.garage474.mspagamento.domain.venda.ItemVenda;
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

    public static List<ItemVendaResponseDTO> listFromEntity(List<ItemVenda> itensVenda) {
        return itensVenda.stream()
                .map(ItemVendaResponseDTO::fromEntity)
                .toList();
    }
}

package dev.garage474.application.dto;

import dev.garage474.domain.venda.Venda;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class VendaDTO {
    private Integer id;
    private ClienteDTO cliente;
    private List<ItemVendaResponseDTO> items;
    private Double valorTotal;

    public static VendaDTO fromEntity(Venda venda) {
        return VendaDTO.builder()
                .id(venda.getId())
                .cliente(ClienteDTO.fromEntity(venda.getCliente()))
                .items(venda.getItensVenda().stream().map(ItemVendaResponseDTO::fromEntity).toList())
                .build();
    }
}

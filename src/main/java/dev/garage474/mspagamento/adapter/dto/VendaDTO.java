package dev.garage474.mspagamento.adapter.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

import dev.garage474.mspagamento.domain.venda.EnumFormaPagamento;
import dev.garage474.mspagamento.domain.venda.Venda;

@Getter
@Builder
public class VendaDTO {
    private Integer id;
    private EnumFormaPagamento formaPagamento;
    private BigDecimal valorTotal;
    private ClienteDTO cliente;
    private List<ItemVendaResponseDTO> items;

    public static VendaDTO fromEntity(Venda venda) {
        return VendaDTO.builder()
                .id(venda.getId())
                .cliente(ClienteDTO.fromEntity(venda.getCliente()))
                .valorTotal(venda.getValorTotal())
                .formaPagamento(venda.getFormaPagamento())
                .items(venda.getItensVenda().stream().map(ItemVendaResponseDTO::fromEntity).toList())
                .build();
    }
}

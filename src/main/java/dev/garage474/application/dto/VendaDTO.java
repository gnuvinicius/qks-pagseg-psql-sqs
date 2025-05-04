package dev.garage474.application.dto;

import dev.garage474.domain.venda.EnumFormaPagamento;
import dev.garage474.domain.venda.EnumStatus;
import dev.garage474.domain.venda.Venda;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class VendaDTO {
    private Integer id;
    private EnumStatus status;
    private EnumFormaPagamento formaPagamento;
    private BigDecimal valorTotal;
    private ClienteDTO cliente;
    private List<ItemVendaResponseDTO> items;

    public static VendaDTO fromEntity(Venda venda) {
        return VendaDTO.builder()
                .id(venda.getId())
                .cliente(ClienteDTO.fromEntity(venda.getCliente()))
                .valorTotal(venda.getValorTotal())
                .status(venda.getStatus())
                .formaPagamento(venda.getFormaPagamento())
                .items(venda.getItensVenda().stream().map(ItemVendaResponseDTO::fromEntity).toList())
                .build();
    }
}

package dev.garage474.mspagamento.adapter.dto;

import dev.garage474.mspagamento.domain.cadastro.Cliente;
import dev.garage474.mspagamento.domain.cadastro.Endereco;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClienteDTO {

    private Integer id;
    private String nome;
    private Endereco endereco;
    private String telefone;
    private String email;

    public static ClienteDTO fromEntity(Cliente cliente) {
        return ClienteDTO.builder()
                .id(null)
                .nome(cliente.getNome())
                .endereco(cliente.getEndereco())
                .telefone(cliente.getTelefone())
                .email(cliente.getEmail())
                .build();
    }
}

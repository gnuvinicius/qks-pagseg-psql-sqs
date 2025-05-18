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
    private String endereco;
    private String telefone;
    private String email;

    public static ClienteDTO fromEntity(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .endereco(cliente.getEndereco().toString())
                .telefone(cliente.getTelefone())
                .email(cliente.getEmail())
                .build();
    }
}

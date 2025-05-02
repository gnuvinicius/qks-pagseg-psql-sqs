package dev.garage474.application.dto;

import dev.garage474.domain.cadastro.Produto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProdutoDTO {
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;

    public static ProdutoDTO fromEntity(Produto produto) {
        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .quantidade(produto.getQuantidade())
                .build();
    }
}

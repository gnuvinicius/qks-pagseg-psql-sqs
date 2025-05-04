package dev.garage474.domain.venda;

import java.math.BigDecimal;

import dev.garage474.application.dto.ItemVendaRequestDTO;
import dev.garage474.application.dto.RealizaVendaRequestDTO;
import dev.garage474.domain.AbastractUseCase;
import dev.garage474.domain.cadastro.Cliente;
import dev.garage474.domain.cadastro.Produto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import lombok.Setter;

@RequestScoped
@Setter
public class EfetuaVendaUseCase extends AbastractUseCase<RealizaVendaRequestDTO> {

    private RealizaVendaRequestDTO request;
    private Venda venda;
    private Cliente cliente;

    @Override
    @Transactional
    public void executa() {
        this.cliente = Cliente.findByIdOptional(request.getClienteId())
                .map(Cliente.class::cast)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        this.venda = new Venda(this.cliente, request.getFormaPagamento());
        this.venda.persist();
        buildItemsVendaEntity();
        finalizaVenda();
    }

    private void finalizaVenda() {
        this.venda.setValorTotal(this.venda.getItensVenda().stream()
                    .map(ItemVenda::getPrecoTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
        this.venda.atualizaStatus(EnumStatus.PENDENTE_PAGAMENTO);
        this.venda.persist();
    }

    private void buildItemsVendaEntity() {
        for (ItemVendaRequestDTO item : request.getItems()) {
            Produto produto = Produto.findByIdOptional(item.getProdutoId())
                    .map(Produto.class::cast)
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

            this.venda.addItem(produto, item.getQuantidade());
        }
    }

}

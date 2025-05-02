package dev.garage474.domain.venda;

import dev.garage474.application.dto.ItemVendaRequestDTO;
import dev.garage474.application.dto.RealizaVendaRequestDTO;
import dev.garage474.domain.AbastractUseCase;
import dev.garage474.domain.cadastro.Cliente;
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
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        this.venda = new Venda(Cliente.getClienteById(this.cliente.getId()), request.getFormaPagamento());
        this.venda.persist();

        buildItemsVendaEntity();
    }

    private void buildItemsVendaEntity() {
        for (ItemVendaRequestDTO item : request.getItems()) {
            this.venda.addItem(item.getProdutoId(), item.getQuantidade());
        }
    }

}

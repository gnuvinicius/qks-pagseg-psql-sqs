package dev.garage474.mspagamento.application.usecase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import dev.garage474.mspagamento.adapter.dto.ItemVendaRequestDTO;
import dev.garage474.mspagamento.adapter.dto.RealizaVendaRequestDTO;
import dev.garage474.mspagamento.application.ports.output.ClienteRepository;
import dev.garage474.mspagamento.application.ports.output.ProdutoRepository;
import dev.garage474.mspagamento.application.ports.output.VendaRepository;
import dev.garage474.mspagamento.domain.cadastro.Cliente;
import dev.garage474.mspagamento.domain.cadastro.Produto;
import dev.garage474.mspagamento.domain.venda.EnumStatus;
import dev.garage474.mspagamento.domain.venda.HistoricoStatusVenda;
import dev.garage474.mspagamento.domain.venda.ItemVenda;
import dev.garage474.mspagamento.domain.venda.Venda;
import jakarta.transaction.Transactional;
import lombok.Setter;

@Setter
@Component
public class EfetuaVendaUseCase extends AbastractUseCase {
    private ClienteRepository clienteRepository;
    private ProdutoRepository produtoRepository;
    private VendaRepository vendaRepository;

    private RealizaVendaRequestDTO request;
    private Venda venda;
    private Cliente cliente;

    public EfetuaVendaUseCase(ClienteRepository clienteRepository,
            VendaRepository vendaRepository,
            ProdutoRepository produtoRepository) {
        this.clienteRepository = clienteRepository;
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    @Transactional
    public void executa() {

        this.cliente = clienteRepository.findById(request.getClienteId());

        this.venda = new Venda(this.cliente, request.getFormaPagamento());
        this.vendaRepository.persist(venda);
        salvaItemsVendaSelecaoCliente();
        finalizaVenda();
    }

    private void finalizaVenda() {
        this.venda.setValorTotal(this.venda.getItensVenda().stream()
                .map(ItemVenda::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        var historico = HistoricoStatusVenda.builder()
                .venda(venda)
                .status(EnumStatus.PENDENTE_PAGAMENTO)
                .criadoEm(LocalDateTime.now())
                .build();

        this.vendaRepository.salvaStatusVenda(historico);
        this.vendaRepository.persist(this.venda);
    }

    private void salvaItemsVendaSelecaoCliente() {
        for (ItemVendaRequestDTO item : request.getItems()) {
            Produto produto = produtoRepository.findById(item.getProdutoId());
            this.venda.addItem(produto, item.getQuantidade());
        }
    }

}

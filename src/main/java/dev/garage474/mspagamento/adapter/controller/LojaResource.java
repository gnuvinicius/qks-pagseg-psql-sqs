package dev.garage474.mspagamento.adapter.controller;

import java.util.List;

import dev.garage474.mspagamento.adapter.dto.ClienteDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.garage474.mspagamento.adapter.dto.RealizaVendaRequestDTO;
import dev.garage474.mspagamento.adapter.dto.VendaDTO;
import dev.garage474.mspagamento.application.ports.output.ClienteRepository;
import dev.garage474.mspagamento.application.ports.output.ProdutoRepository;
import dev.garage474.mspagamento.application.ports.output.VendaRepository;
import dev.garage474.mspagamento.application.usecase.EfetuaVendaUseCase;
import dev.garage474.mspagamento.domain.cadastro.Cliente;
import dev.garage474.mspagamento.domain.cadastro.Produto;
import dev.garage474.mspagamento.domain.venda.Venda;

@RestController
@RequestMapping("/loja")
public class LojaResource {

    private final EfetuaVendaUseCase efetuaVendaUseCase;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final VendaRepository vendaRepository;

    LojaResource(EfetuaVendaUseCase efetuaVendaUseCase,
            ProdutoRepository produtoRepository,
            VendaRepository vendaRepository,
            ClienteRepository clienteRepository) {
        this.efetuaVendaUseCase = efetuaVendaUseCase;
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/produtos")
    public ResponseEntity<?> getProdutos() {
        List<Produto> result = produtoRepository.listarProdutos();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/clientes")
    public ResponseEntity<?> getClientes() {
        List<Cliente> clientes = clienteRepository.listarClientes();
        var result = clientes.stream().map(ClienteDTO::fromEntity).toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/vendas")
    public ResponseEntity<?> getVendas() {
        List<Venda> vendas = vendaRepository.listarVendas();
        var result = vendas.stream().map(VendaDTO::fromEntity).toList();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/realiza-venda")
    public ResponseEntity<?> realizaVenda(@RequestBody RealizaVendaRequestDTO request) {

        try {
            efetuaVendaUseCase.setRequest(request);
            efetuaVendaUseCase.executa();
            return ResponseEntity.ok("Venda realizada com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

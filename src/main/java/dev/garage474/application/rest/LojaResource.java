package dev.garage474.application.rest;

import dev.garage474.application.dto.RealizaVendaRequestDTO;
import dev.garage474.application.dto.VendaDTO;
import dev.garage474.domain.cadastro.Cliente;
import dev.garage474.domain.cadastro.Produto;
import dev.garage474.domain.venda.EfetuaVendaUseCase;
import dev.garage474.domain.venda.Venda;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;


@Path("/loja")
public class LojaResource {

    @Inject
    private EfetuaVendaUseCase efetuaVendaUseCase;

    @GET
    @Path("/produtos")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> getProdutos() {
        List<Produto> result = Produto.listAll();
        return RestResponse.ok(result);
    }

    @GET
    @Path("/clientes")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> getClientes() {
        List<Cliente> result = Cliente.listAll();
        return RestResponse.ok(result);
    }

    @GET
    @Path("/vendas")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> getVendas() {
        List<Venda> vendas = Venda.listAll();

        var result = vendas.stream().map(VendaDTO::fromEntity).toList();
        return RestResponse.ok(result);
    }

    @POST
    @Path("/realiza-venda")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse<?> realizaVenda(RealizaVendaRequestDTO request) {

        try {
            efetuaVendaUseCase.setRequest(request);
            efetuaVendaUseCase.executa();
            return RestResponse.ok("Venda realizada com sucesso");
        } catch (IllegalArgumentException e) {
            return RestResponse.status(400, e.getMessage());
        }
    }
}

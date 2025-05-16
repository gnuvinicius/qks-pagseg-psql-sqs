package dev.garage474.mspagamento.infraestructure.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import dev.garage474.mspagamento.application.ports.output.VendaRepository;
import dev.garage474.mspagamento.domain.venda.HistoricoStatusVenda;
import dev.garage474.mspagamento.domain.venda.Venda;

@Repository
public class VendaRepositoryImpl implements VendaRepository {

  @Override
  public Venda findById(Integer id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public void persist(Venda venda) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'persist'");
  }

  @Override
  public void salvaStatusVenda(HistoricoStatusVenda historico) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'salvaStatusVenda'");
  }

  @Override
  public List<Venda> listarVendas() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'listarVendas'");
  }
    
    
  
}

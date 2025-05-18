package dev.garage474.mspagamento.infraestructure.repository;

import java.util.List;

import dev.garage474.mspagamento.domain.venda.ItemVenda;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.garage474.mspagamento.application.ports.output.VendaRepository;
import dev.garage474.mspagamento.domain.venda.HistoricoStatusVenda;
import dev.garage474.mspagamento.domain.venda.Venda;

@Repository
public class VendaRepositoryImpl implements VendaRepository {

    @Autowired
    EntityManager em;

    @Override
    public Venda findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void salvaVenda(Venda venda) {
        em.persist(venda);
    }

    @Override
    public void salvaStatusVenda(HistoricoStatusVenda historico) {
        em.persist(historico);
    }

    @Override
    public List<Venda> listarVendas() {
        String jpql = "SELECT v FROM Venda v "
                + "LEFT JOIN FETCH v.cliente c "
                + "LEFT JOIN FETCH v.itensVenda i "
                + "LEFT JOIN FETCH i.produto p ORDER BY v.dataVenda DESC";
        return em.createQuery(jpql, Venda.class)
                .getResultList();
    }

    @Override
    public void salvaItemVenda(ItemVenda itemVenda) {
        em.persist(itemVenda);
    }


}

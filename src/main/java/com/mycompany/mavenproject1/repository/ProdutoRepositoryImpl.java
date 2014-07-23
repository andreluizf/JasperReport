package com.mycompany.mavenproject1.repository;

import com.mycompany.mavenproject1.model.AlteracaoModulo;
import com.mycompany.mavenproject1.model.Modulo;
import com.mycompany.mavenproject1.model.Produto;
import com.mycompany.mavenproject1.model.ProdutoRelatorio;
import com.mycompany.mavenproject1.model.Versionamento;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "ProdutoRepositoryImplTest", mappedName = "ejb/ProdutoRepositoryImplTEst")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProdutoRepositoryImpl implements ProdutoRepositoryTest {

    @PersistenceContext(unitName = "CAX")
    EntityManager em;

    @Override
    public void saveOrUpdate(Produto modelo) {
        if (modelo.getId() == null) {
            em.persist(modelo);
        } else {
            em.merge(modelo);
        }
    }

    @Override
    public void delete(Produto modelo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Produto> findAll() {
        return em.createQuery("SELECT p FROM Produto p ", Produto.class).getResultList();
    }

    @Override
    public Produto findById(Long id) {
        try {
            return em.createQuery("SELECT p FROM Produto p where p.id= :id", Produto.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Produto> findPagination(int first, int max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long rowCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Versionamento> findVersionamento(Integer id) {
        return em.createQuery("SELECT v FROM Versionamento v where v.idProduto.id=:id").setParameter("id", id).getResultList();
    }

    @Override
    public List<Modulo> findModuloByProduto(Integer id) {
        return em.createQuery("SELECT m FROM Modulo m where m.idProduto.id=:id").setParameter("id", id).getResultList();
    }

    @Override
    public List<AlteracaoModulo> findAlteracaoModulos(Integer id) {
        return em.createQuery("SELECT m FROM AlteracaoModulo m JOIN FETCH m.idVersionamento v where m.idModulo.id =:id").setParameter("id", id).getResultList();
    }

    @Override
    public String findUltimaVersaoByProduto(Integer id) {
        try {
            return em.createQuery("SELECT m.versao FROM Versionamento m  where m.idProduto.id =:id ORDER BY m.id DESC ").setParameter("id", id).setMaxResults(1).getSingleResult().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Modulo findModulo(Integer id) {
        try {
            return em.createQuery("SELECT m FROM Modulo m  where m.id =:id ", Modulo.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Versionamento findVersao(Integer id) {
        try {
            return em.createQuery("SELECT m FROM Versionamento m  where m.id =:id ", Versionamento.class).setParameter("id", id).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ProdutoRelatorio> findProdutoRelatorio(Integer id) {
        List<Produto> lists = em.createQuery("SELECT p FROM Produto p INNER JOIN p.versionamentoList v INNER JOIN v.alteracaoModuloList am INNER JOIN am.idModulo m where p.id =:id").setParameter("id", id).getResultList();
        List<ProdutoRelatorio> relatorios = new ArrayList<>();

        for (Produto produto : lists) {
            for (Versionamento versao : produto.getVersionamentoList()) {
                for (AlteracaoModulo alteracao : versao.getAlteracaoModuloList()) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String data = dateFormat.format(versao.getData());
                    relatorios.add(new ProdutoRelatorio(produto.getDescricao(), versao.getVersao(), data, alteracao.getIdModulo().getDescricao(), alteracao.getDescricao()));
                }
            }

        }
        return relatorios;
    }

}

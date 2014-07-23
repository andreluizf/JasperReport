package com.mycompany.mavenproject1.repository;

import com.mycompany.mavenproject1.model.AlteracaoModulo;
import com.mycompany.mavenproject1.model.Modulo;
import com.mycompany.mavenproject1.model.Produto;
import com.mycompany.mavenproject1.model.ProdutoRelatorio;
import com.mycompany.mavenproject1.model.Versionamento;
import java.util.List;

public interface ProdutoRepositoryTest extends Repository<Produto> {

    public List<Versionamento> findVersionamento(Integer id);

    public List<Modulo> findModuloByProduto(Integer id);

    public Modulo findModulo(Integer id);

    public List<AlteracaoModulo> findAlteracaoModulos(Integer id);

    public String findUltimaVersaoByProduto(Integer id);

    public Versionamento findVersao(Integer id);

    public List<ProdutoRelatorio> findProdutoRelatorio(Integer id);
}

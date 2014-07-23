/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import com.mycompany.mavenproject1.model.ProdutoRelatorio;
import com.mycompany.mavenproject1.repository.ProdutoRepositoryTest;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
public class RelatorioBean {

    @EJB(beanName = "ProdutoRepositoryImplTest")
    ProdutoRepositoryTest rep;

    public void gerarRelatorio() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        String arquivo = context.getRealPath("productAllVersionParametros.jasper");
        HashMap<String, Object> params = new HashMap<String, Object>();
//        ProdutoRelatorio rel = new ProdutoRelatorio("produto1", "1", new Date().toString(), "modulo1", " descricao 1");
//        ProdutoRelatorio rel2 = new ProdutoRelatorio("produto1", "1", new Date().toString(), "modulo2", " descricao 3");
//        ProdutoRelatorio rel3 = new ProdutoRelatorio("produto1", "1", new Date().toString(), "modulo3", " descricao 4");
//        ProdutoRelatorio rel1 = new ProdutoRelatorio("produto1", "2", new Date().toString(), "modulo 1", " descricao 2");
//        ProdutoRelatorio rel4 = new ProdutoRelatorio("produto1", "3", new Date().toString(), "modulo2", " descricao 5");
//        List<ProdutoRelatorio> lists = new ArrayList<>();
//        lists.add(rel);
//        lists.add(rel2);
//        lists.add(rel3);
//        lists.add(rel1);
//        lists.add(rel4);
        List<ProdutoRelatorio> relatorios=rep.findProdutoRelatorio(20);
        JRDataSource jrds = new JRBeanCollectionDataSource(relatorios);
        params.put("logo", context.getRealPath("logo.png"));

        gerarRelatorioWeb(jrds, params, arquivo);
    }

    private void gerarRelatorioWeb(JRDataSource jrds, Map<String, Object> parametros, String arquivo) {
        ServletOutputStream servletOutputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        Connect con = new Connect();
        try {

            servletOutputStream = response.getOutputStream();
            JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)), response.getOutputStream(), parametros, jrds);
            response.setContentType("application/pdf");
            servletOutputStream.flush();
            servletOutputStream.close();
            context.renderResponse();
            context.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.fecharConexao();
        }

    }

}

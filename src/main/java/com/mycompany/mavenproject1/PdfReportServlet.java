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
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@WebServlet("/report.pdf")
public class PdfReportServlet extends HttpServlet {

    @EJB(beanName = "ProdutoRepositoryImplTest")
    ProdutoRepositoryTest rep;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String arquivo = request.getSession().getServletContext().getRealPath("/productAllVersionParametros.jasper");

        HashMap<String, Object> params = new HashMap<String, Object>();
        String id = request.getParameter("id");
        System.out.println("id " + id);
        List<ProdutoRelatorio> relatorios = rep.findProdutoRelatorio(Integer.parseInt(id));
        JRDataSource jrds = new JRBeanCollectionDataSource(relatorios);
        params.put("logo", request.getSession().getServletContext().getRealPath("logo.png"));

        ServletOutputStream servletOutputStream = null;

        try {
            byte[] p = JasperRunManager.runReportToPdf(new FileInputStream(new File(arquivo)), params, jrds);
            response.setContentType("application/pdf");
            response.setContentLength(p.length);
            response.addHeader("Content-disposition", "inline; filename=rel.pdf");
            response.getOutputStream().write(p);

        } catch (Exception e) {
            e.printStackTrace();
        }

//        String id = request.getParameter("id");
//        byte[] content = (byte[]) request.getSession().getAttribute(id);
//        response.setContentType("application/pdf");
//        response.setContentLength(content.length);
//        response.addHeader("Content-disposition", "inline; filename=rel.pdf");
//        response.getOutputStream().write(content);
    }

}

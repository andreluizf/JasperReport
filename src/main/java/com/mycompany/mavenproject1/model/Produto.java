/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.mavenproject1.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author andre-pc
 */
@Entity
@Table(name = "PRODUTO")
@NamedQueries({
    @NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")})
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Size(max = 50)
    @Column(name = "DESCRICAO")
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_LICENCA")
    private BigDecimal valorLicenca;
    @Column(name = "VALOR_MANUTENCAO")
    private BigDecimal valorManutencao;
    @Column(name = "VALOR_IMPLANTACAO")
    private BigDecimal valorImplantacao;
    @Size(max = 255)
    @Column(name = "APLICACAO")
    private String aplicacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProduto")
    private List<Modulo> moduloList;
    @OneToMany(mappedBy = "idProduto")
    private List<Versionamento> versionamentoList;

    public Produto() {
    }

    public Produto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorLicenca() {
        return valorLicenca;
    }

    public void setValorLicenca(BigDecimal valorLicenca) {
        this.valorLicenca = valorLicenca;
    }

    public BigDecimal getValorManutencao() {
        return valorManutencao;
    }

    public void setValorManutencao(BigDecimal valorManutencao) {
        this.valorManutencao = valorManutencao;
    }

    public BigDecimal getValorImplantacao() {
        return valorImplantacao;
    }

    public void setValorImplantacao(BigDecimal valorImplantacao) {
        this.valorImplantacao = valorImplantacao;
    }

    public String getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(String aplicacao) {
        this.aplicacao = aplicacao;
    }

    public List<Modulo> getModuloList() {
        return moduloList;
    }

    public void setModuloList(List<Modulo> moduloList) {
        this.moduloList = moduloList;
    }

    public List<Versionamento> getVersionamentoList() {
        return versionamentoList;
    }

    public void setVersionamentoList(List<Versionamento> versionamentoList) {
        this.versionamentoList = versionamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produto)) {
            return false;
        }
        Produto other = (Produto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.model.Produto[ id=" + id + " ]";
    }
    
}

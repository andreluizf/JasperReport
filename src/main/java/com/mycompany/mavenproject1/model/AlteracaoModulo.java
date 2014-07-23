/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.mavenproject1.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author andre-pc
 */
@Entity
@Table(name = "ALTERACAO_MODULO")
@NamedQueries({
    @NamedQuery(name = "AlteracaoModulo.findAll", query = "SELECT a FROM AlteracaoModulo a")})
public class AlteracaoModulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "DESCRICAO")
    private String descricao;
    @Column(name = "DATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @JoinColumn(name = "ID_VERSIONAMENTO", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Versionamento idVersionamento;
    @JoinColumn(name = "ID_MODULO", referencedColumnName = "ID")
    @ManyToOne
    private Modulo idModulo;

    public AlteracaoModulo() {
    }

    public AlteracaoModulo(Integer id) {
        this.id = id;
    }

    public AlteracaoModulo(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Versionamento getIdVersionamento() {
        return idVersionamento;
    }

    public void setIdVersionamento(Versionamento idVersionamento) {
        this.idVersionamento = idVersionamento;
    }

    public Modulo getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Modulo idModulo) {
        this.idModulo = idModulo;
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
        if (!(object instanceof AlteracaoModulo)) {
            return false;
        }
        AlteracaoModulo other = (AlteracaoModulo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.model.AlteracaoModulo[ id=" + id + " ]";
    }
    
}

package com.mycompany.mavenproject1.model;

public class ProdutoRelatorio {

    private String produto;
    private String versao;
    private String data;
    private String modulo;
    private String descricao;

    public ProdutoRelatorio(String produto, String versao, String data, String modulo, String descricao) {
        this.produto = produto;
        this.versao = versao;
        this.data = data;
        this.modulo = modulo;
        this.descricao = descricao;
    }

    public ProdutoRelatorio() {
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

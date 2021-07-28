package br.jamtech.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProdutoModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String descricao;
	private int quantidade;
	private Double preco;
	private Double preco_custo;
	private String status;
	private int codigo_barra;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Double getPreco_custo() {
		return preco_custo;
	}
	public void setPreco_custo(Double preco_custo) {
		this.preco_custo = preco_custo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCodigo_barra() {
		return codigo_barra;
	}
	public void setCodigo_barra(int codigo_barra) {
		this.codigo_barra = codigo_barra;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}

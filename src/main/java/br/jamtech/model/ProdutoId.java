package br.jamtech.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity
public class ProdutoId  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private Long idProduto;
	@NotNull
	private int qunatidadeProdutoVendido;
	@NotNull
	private Long idVenda;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public int getQunatidadeProdutoVendido() {
		return qunatidadeProdutoVendido;
	}
	public void setQunatidadeProdutoVendido(int qunatidadeProdutoVendido) {
		this.qunatidadeProdutoVendido = qunatidadeProdutoVendido;
	}
	public Long getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	

}

package br.jamtech.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class ProdutoModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message="Nome não pode ser nulo")
	private String descricao;
	
	private int quantidade;
	@NotNull(message="Nome não pode ser nulo")
	private Double preco;
	private Double preco_custo;
	private String status;
	private int codigo_barra;
	private int quantidade_vendido;
	private String finalizavenda;
	@ManyToMany(mappedBy = "listaproduto",cascade = CascadeType.MERGE)// inserir atomico, em cascata
	private List<VendaModel> vendaModel;
	
	
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
	public List<VendaModel> getVendaModel() {
		return vendaModel;
	}
	public void setVendaModel(List<VendaModel> vendaModel) {
		this.vendaModel = vendaModel;
	}
	public int getQuantidade_vendido() {
		return quantidade_vendido;
	}
	public void setQuantidade_vendido(int quantidade_vendido) {
		this.quantidade_vendido = quantidade_vendido;
	}
	public String getFinalizavenda() {
		return finalizavenda;
	}
	public void setFinalizavenda(String finalizavenda) {
		this.finalizavenda = finalizavenda;
	}
	@Override
	public String toString() {
		return "ProdutoModel [id=" + id + ", descricao=" + descricao + ", quantidade=" + quantidade + ", preco=" + preco
				+ ", preco_custo=" + preco_custo + ", status=" + status + ", codigo_barra=" + codigo_barra
				+ ", quantidade_vendido=" + quantidade_vendido + ", finalizavenda=" + finalizavenda + ", vendaModel="
				+ vendaModel + "]";
	}
	
	
	
	

}

package br.jamtech.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class VendaModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private ClienteModel clienteModel;
	
	//@NotNull(message="Produto não pode ser nulo")
	@ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.MERGE )
	private List<ProdutoModel> listaproduto= new ArrayList<>();
	
	
	private Date data;
	private String formaPagamento;
	
	
	

	
	public List<ProdutoModel> getListaproduto() {
		return listaproduto;
	}
	public void setListaproduto(List<ProdutoModel> listaproduto) {
		this.listaproduto = listaproduto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	public ClienteModel getClienteModel() {
		return clienteModel;
	}
	public void setClienteModel(ClienteModel clienteModel) {
		this.clienteModel = clienteModel;
	}
	public void addProduto(ProdutoModel produto){
		
			getListaproduto().add(produto);
		
	}
	
	

}

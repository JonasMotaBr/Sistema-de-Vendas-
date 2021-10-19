package br.jamtech.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class EnderecoClienteModel implements Serializable{

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String rua_EnderecoCliente;
	private String bairro_EnderecoCliente;
	private String cep_EnderecoCliente;
	private String Estado_EnderecoCliente;
	private String numero_EnderecoCliente;
	@ManyToOne
	private ClienteModel clienteModel;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRua_EnderecoCliente() {
		return rua_EnderecoCliente;
	}
	public void setRua_EnderecoCliente(String rua_EnderecoCliente) {
		this.rua_EnderecoCliente = rua_EnderecoCliente;
	}
	public String getBairro_EnderecoCliente() {
		return bairro_EnderecoCliente;
	}
	public void setBairro_EnderecoCliente(String bairro_EnderecoCliente) {
		this.bairro_EnderecoCliente = bairro_EnderecoCliente;
	}
	public String getCep_EnderecoCliente() {
		return cep_EnderecoCliente;
	}
	public void setCep_EnderecoCliente(String cep_EnderecoCliente) {
		this.cep_EnderecoCliente = cep_EnderecoCliente;
	}
	public String getEstado_EnderecoCliente() {
		return Estado_EnderecoCliente;
	}
	public void setEstado_EnderecoCliente(String estado_EnderecoCliente) {
		Estado_EnderecoCliente = estado_EnderecoCliente;
	}
	public String getNumero_EnderecoCliente() {
		return numero_EnderecoCliente;
	}
	public void setNumero_EnderecoCliente(String numero_EnderecoCliente) {
		this.numero_EnderecoCliente = numero_EnderecoCliente;
	}
	
	
	
	
	
}

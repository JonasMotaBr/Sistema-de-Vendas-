package br.jamtech.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class UsuarioModel implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message="Login não pode ser nulo")
	@NotEmpty(message="Login não pode ser vazio")
	private String loginUsu;
	
	@NotNull(message="Nome não pode ser nulo")
	@NotEmpty(message="Nome não pode ser vazio")
	private String nomeUsu;
	
	@NotNull(message="sobrenome não pode ser nulo")
	@NotEmpty(message="sobrenome não pode ser vazio")
	private String sobrenomeUsu;

	@NotNull(message="E-mail não pode ser nulo")
	@NotEmpty(message="E-mail não pode ser vazio")
	private String emailUsu;
	private int idadeUsu;
	
	@NotNull(message="Senha não pode ser nulo")
	@NotEmpty(message="Senha não pode ser vazio")
	private String senhaUsu;
	
	@NotNull(message="Repita senha não pode ser nulo")
	@NotEmpty(message="Repita senha pode ser vazio")
	private String repitaSenhaUsu;

	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginUsu() {
		return loginUsu;
	}

	public void setLoginUsu(String loginUsu) {
		this.loginUsu = loginUsu;
	}

	public String getNomeUsu() {
		return nomeUsu;
	}

	public void setNomeUsu(String nomeUsu) {
		this.nomeUsu = nomeUsu;
	}

	public String getSobrenomeUsu() {
		return sobrenomeUsu;
	}

	public void setSobrenomeUsu(String sobrenomeUsu) {
		this.sobrenomeUsu = sobrenomeUsu;
	}

	public String getEmailUsu() {
		return emailUsu;
	}

	public void setEmailUsu(String emailUsu) {
		this.emailUsu = emailUsu;
	}

	public int getIdadeUsu() {
		return idadeUsu;
	}

	public void setIdadeUsu(int idadeUsu) {
		this.idadeUsu = idadeUsu;
	}

	public String getSenhaUsu() {
		return senhaUsu;
	}

	public void setSenhaUsu(String senhaUsu) {
		this.senhaUsu = senhaUsu;
	}

	public String getRepitaSenhaUsu() {
		return repitaSenhaUsu;
	}

	public void setRepitaSenhaUsu(String repitaSenhaUsu) {
		this.repitaSenhaUsu = repitaSenhaUsu;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UsuarioModel [id=" + id + ", loginUsu=" + loginUsu + ", nomeUsu=" + nomeUsu + ", sobrenomeUsu="
				+ sobrenomeUsu + ", emailUsu=" + emailUsu + ", idadeUsu=" + idadeUsu + ", senhaUsu=" + senhaUsu
				+ ", repitaSenhaUsu=" + repitaSenhaUsu + "]";
	}

	
	
	
	
	
	
	

}

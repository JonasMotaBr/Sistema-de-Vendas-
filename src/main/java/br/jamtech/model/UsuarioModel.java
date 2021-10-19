package br.jamtech.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.jamtech.security.Role;

@Entity
public class UsuarioModel implements UserDetails {
	
	
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

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuarios_roles", 
	joinColumns = @JoinColumn(name="usuario_id",
	referencedColumnName = "id",
	table = "UsuarioModel"),
	
	inverseJoinColumns = @JoinColumn(name="role_id",
	referencedColumnName = "id",
	table = "role")
			)
	
	private List<Role> roles;
	
	@Override
	public String toString() {
		return "UsuarioModel [id=" + id + ", loginUsu=" + loginUsu + ", nomeUsu=" + nomeUsu + ", sobrenomeUsu="
				+ sobrenomeUsu + ", emailUsu=" + emailUsu + ", idadeUsu=" + idadeUsu + ", senhaUsu=" + senhaUsu
				+ ", repitaSenhaUsu=" + repitaSenhaUsu + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return senhaUsu;
	}

	@Override
	public String getUsername() {
		return loginUsu;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
	
	
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

	
	
	
	
	
	
	
	

}

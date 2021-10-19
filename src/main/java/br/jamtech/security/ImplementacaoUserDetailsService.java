package br.jamtech.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.jamtech.model.UsuarioModel;
import br.jamtech.repository.UsuarioRepository;



@Service
public class ImplementacaoUserDetailsService implements UserDetailsService  {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UsuarioModel usuario = usuarioRepository.buscarPorLogin(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario não foi encontrado");
		}
		
		return new User(usuario.getLoginUsu(), 
				usuario.getPassword(), 
				usuario.isEnabled(), 
				usuario.isAccountNonExpired(), 
				usuario.isCredentialsNonExpired(), 
				usuario.isAccountNonLocked(), 
				usuario.getAuthorities());
	}

	
	
	
}

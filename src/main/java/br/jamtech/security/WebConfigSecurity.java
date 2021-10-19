package br.jamtech.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter{


	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	
	@Override // Configura as solicitaÃ§Ãµes de acesso por Http
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf()
		.disable() // Desativa as configuraÃ§Ãµes padrÃ£o de memÃ³ria.
		.authorizeRequests() // Pertimir restringir acessos
		.antMatchers(HttpMethod.GET, "/").permitAll() // Qualquer usuÃ¡rio acessa a pagina inicial
		.antMatchers(HttpMethod.GET, "/cadastrarusuario").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and().formLogin().permitAll() // permite qualquer usuÃ¡rio
		.loginPage("/login")
		.defaultSuccessUrl("/adminpag")
		.failureUrl("/login?error=true")
		.and().logout() // Mapeia URL de Logout e invalida usuÃ¡rio autenticado
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.and()
        .exceptionHandling().accessDeniedPage("/403");
	
	}
	
	
	@Override // Cria autenticaÃ§Ã£o do usuÃ¡rio com banco de dados ou em memÃ³ria
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(implementacaoUserDetailsService)
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
		
		
		//Valida sem banco de dados
		/*
		 * auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
		 * .withUser("alex") .password("123") .roles("ADMIN");
		 */
	}
	
	@Override // Ignora URL especificas
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/materialize/**");
	}
	
}

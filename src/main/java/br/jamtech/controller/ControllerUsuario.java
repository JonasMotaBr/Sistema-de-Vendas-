package br.jamtech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.jamtech.model.ClienteModel;
import br.jamtech.model.UsuarioModel;
import br.jamtech.repository.UsuarioRepository;

@Controller
public class ControllerUsuario {

	@Autowired
	UsuarioRepository usuarioreRpository;
	
	
	public String getUsuarioLogado(){
		//passar usuario 
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				String username="";
				if (principal instanceof UserDetails) {
				 username = ((UserDetails)principal).getUsername();
				} else {
				 username = principal.toString();
				}
		return username;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/adminpag")
	public ModelAndView inicio() {
		ModelAndView modelview = new ModelAndView("admin/adminpag");
		
		modelview.addObject("username", getUsuarioLogado());
		return modelview;
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/pagina_usuario")
	public ModelAndView paginaUsuario() {
		// ******* Pegar usuario local para exibir ****************
		ModelAndView modelview = new ModelAndView("/usuarioTemp/pagina_usuario");
//		Iterable<UsuarioModel> usuarioIt = usuarioreRpository.findAll();
//		modelview.addObject("produtos", usuarioIt);
		return modelview;
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrarusuario")
	public ModelAndView paginaCadastrarUsuario() {
		ModelAndView modelview = new ModelAndView("/usuarioTemp/cadastrarusuario");
		return modelview;
	}

	@RequestMapping(method = RequestMethod.POST, value = "**/addusuario")
	public ModelAndView addUsuario(UsuarioModel usuarioModel) {
		usuarioreRpository.save(usuarioModel);
		ModelAndView modelview = new ModelAndView("/usuarioTemp/pagina_usuario");
		Iterable<UsuarioModel> usuarios = usuarioreRpository.findAll();
		modelview.addObject("usuarios", usuarios);
		modelview.addObject("usuarioObj", new UsuarioModel());
		return modelview;
	}
	

	
	
	
	
}

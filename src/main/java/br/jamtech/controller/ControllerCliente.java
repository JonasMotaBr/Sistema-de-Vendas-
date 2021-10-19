package br.jamtech.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.jamtech.model.ClienteModel;
import br.jamtech.model.ProdutoModel;
import br.jamtech.repository.ClienteRepository;

@Controller
public class ControllerCliente {

	
	@Autowired
	ClienteRepository clienteRepository;
	
	

	
	
	@RequestMapping(method = RequestMethod.GET, value = "/telaclientes")
	public ModelAndView TelaListaCliente() {
		ModelAndView modelview = new ModelAndView("/clienteTemp/clientepag");
		Iterable<ClienteModel> listaClientes = clienteRepository.findAll();
		
		modelview.addObject("username", getUsuarioLogado());
		modelview.addObject("clientes", listaClientes);
		return modelview;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrarclientes")
	public ModelAndView telacadastrarcliente(){
		ModelAndView modelview = new ModelAndView("/clienteTemp/cadastrarclientes");
		modelview.addObject("username", getUsuarioLogado());
		return modelview;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "**/actioncadastrarclientes")
	public ModelAndView cadastrarClientes(ClienteModel clienteModel) {
		clienteRepository.save(clienteModel);
		ModelAndView modelview = new ModelAndView("/clienteTemp/clientepag");
		Iterable<ClienteModel> clientes = clienteRepository.findAll();
		modelview.addObject("username", getUsuarioLogado());
		modelview.addObject("clientes", clientes);
		modelview.addObject("produtoObj", new ProdutoModel());
		return modelview;
	}
	
	@GetMapping("/removerCliente/{idcliente}")
	public ModelAndView removerCliente(@PathVariable("idcliente") Long idcliente) {

		clienteRepository.deleteById(idcliente);
		ModelAndView modelview = new ModelAndView("/clienteTemp/clientepag");
		// carrega todas pessoas para pessoaObj, e em seguida resgata na lista
		Iterable<ClienteModel> clienteIt = clienteRepository.findAll();
		modelview.addObject("username", getUsuarioLogado());
		modelview.addObject("clientes", clienteIt);
		return modelview;
	}
	
	// mapea editarpessoa mais o parametro idpessoa
	@GetMapping("/editarCliente/{idcliente}")
	public ModelAndView editarCliente(@PathVariable("idcliente") Long idcliente) {
		ModelAndView modelview = new ModelAndView("/clienteTemp/editarcliente");
		Optional<ClienteModel> clienteIt = clienteRepository.findById(idcliente);
		modelview.addObject("username", getUsuarioLogado());
		modelview.addObject("clienteObj", clienteIt.get());
		return modelview;
	}
	
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
	
	
	
}

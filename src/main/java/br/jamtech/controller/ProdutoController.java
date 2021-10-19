package br.jamtech.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.jamtech.model.ProdutoModel;
import br.jamtech.repository.ProdutoRepository;




@Controller
public class ProdutoController {
	
	@Autowired
	public ProdutoRepository produtoRepository;
	
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/listaprodutos")
	public ModelAndView TelaLista() {
		ModelAndView modelview = new ModelAndView("/produtoTemp/listaprodutos");
		Iterable<ProdutoModel> produtosIt = produtoRepository.findAll();
		modelview.addObject("username", getUsuarioLogado());
		modelview.addObject("produtos", produtosIt);
		return modelview;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrarprodutos")
	public ModelAndView TelaCadastroProdutos() {
		ModelAndView modelview = new ModelAndView("/produtoTemp/cadastrarprodutos");
		modelview.addObject("username", getUsuarioLogado());
		return modelview;
	}
	
	
	
	// ignora antes tudo que vem antes na url
		@RequestMapping(method = RequestMethod.POST, value = "**/cadastrarprodutos")
		public ModelAndView salvarProduto(@Valid ProdutoModel proModel, BindingResult bindingResult) {
			

			if (bindingResult.hasErrors()) {
				ModelAndView modelAndView = new ModelAndView("produtoTemp/cadastrarprodutos");
				Iterable<ProdutoModel> produtosIt = produtoRepository.findAll();
				modelAndView.addObject("username", getUsuarioLogado());
				modelAndView.addObject("produtos", produtosIt);
				modelAndView.addObject("produtoObj", proModel);
				
				List<String> msg = new ArrayList<String>();
				for (ObjectError objectError : bindingResult.getAllErrors()) {
					msg.add(objectError.getDefaultMessage()); // vem das anotaÃ§Ãµes @NotEmpty e outras
				}
				modelAndView.addObject("username", getUsuarioLogado());
				modelAndView.addObject("username", getUsuarioLogado());
				modelAndView.addObject("msg", msg);
				return modelAndView;
			}
			
			
			produtoRepository.save(proModel);
			ModelAndView andView = new ModelAndView("produtoTemp/listaprodutos");
			Iterable<ProdutoModel> pessoasIt = produtoRepository.findAll();
			andView.addObject("produtos", pessoasIt);
			andView.addObject("produtoObj", new ProdutoModel());
			return andView;
		}
		
		
		
		// mapea editarpessoa mais o parametro idpessoa
		@GetMapping("/editarprodutos/{idproduto}")
		public ModelAndView editarProduto(@PathVariable("idproduto") Long idproduto) {
			ModelAndView modelview = new ModelAndView("/produtoTemp/editarprodutos");
			Optional<ProdutoModel> produtoIt = produtoRepository.findById(idproduto);
			modelview.addObject("username", getUsuarioLogado());
			modelview.addObject("produtoObj", produtoIt.get());
			return modelview;
		}
		
		@GetMapping("/removerProduto/{idproduto}")
		public ModelAndView removerProduto(@PathVariable("idproduto") Long idproduto) {

			produtoRepository.deleteById(idproduto);
			ModelAndView modelview = new ModelAndView("/produtoTemp/listaprodutos");
			// carrega todas pessoas para pessoaObj, e em seguida resgata na lista
			Iterable<ProdutoModel> produtoIt = produtoRepository.findAll();
			modelview.addObject("username", getUsuarioLogado());
			modelview.addObject("produtos", produtoIt);
			return modelview;
		}

	
	


}

package br.jamtech.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.jamtech.model.ClienteModel;
import br.jamtech.model.ProdutoId;
import br.jamtech.model.ProdutoModel;
import br.jamtech.model.VendaModel;
import br.jamtech.repository.ClienteRepository;
import br.jamtech.repository.ProdutoRepository;
import br.jamtech.repository.VendaRepository;
import br.jamtech.repository.produtoIdRepository;

@Controller
public class ControllerVenda {

	@Autowired
	private VendaRepository vendaRepository;
	@Autowired
	private ProdutoRepository  produtoRepository;
	@Autowired 
	private produtoIdRepository produtoidrepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
    private List<ProdutoModel> listaproduto = new ArrayList<ProdutoModel>();
	private VendaModel venda = new VendaModel();
	private List<ProdutoModel> listaparalela = new ArrayList<ProdutoModel>();
	private List<Double> valorTotal  = new ArrayList<Double>();;
	private Double val;
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
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/caixavenda")
	public ModelAndView telaVenda(){
		ModelAndView modelview = new ModelAndView("caixaTemp/caixavenda");
		
		modelview.addObject("username", getUsuarioLogado());
		return modelview;
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "**/addvenda")
	public ModelAndView addVenda(VendaModel vendaModel){
		vendaRepository.save(vendaModel);
		ModelAndView modelview = new ModelAndView("/caixaTemp/caixavenda");
		Iterable<VendaModel> vendas = vendaRepository.findAll();
		modelview.addObject("username", getUsuarioLogado());
		modelview.addObject("vendas", vendas);
		modelview.addObject("vendaObj", new VendaModel());
		return modelview;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/pesquisarProdutoVenda")
	public ModelAndView PesquisarProduto(ProdutoModel produtoModel) {

		ModelAndView andView = new ModelAndView("/caixaTemp/caixavenda");
		Iterable<ProdutoModel> produtoIt = produtoRepository.buscarListaProdutoPorDescricao(produtoModel.getDescricao());
		andView.addObject("produtos", produtoIt);
		andView.addObject("username", getUsuarioLogado());
		return andView;
	}
	
//	@GetMapping("/addProdutoVenda/{idproduto}")
//	public ModelAndView addProdutoVenda(@PathVariable("idproduto") Long idproduto) {
//		ModelAndView andView = new ModelAndView("/caixaTemp/caixavenda");
//        System.out.println(idproduto);		
//		return null;
//	} 
	
	@RequestMapping(method = RequestMethod.POST, value = "**/addProdutoVenda")
	public ModelAndView addProdutoListaVenda(ProdutoModel produtoModel, 
			@RequestParam(required = false) String formaPagamento,
			@RequestParam(required = false) String informacliente,
			@RequestParam(required = false) String removerId) {
		
		ModelAndView andView = new ModelAndView("/caixaTemp/caixavenda");
		ModelAndView paginacliente = new ModelAndView("/caixaTemp/informarclientevenda");
		ProdutoModel produto = new ProdutoModel();
		VendaModel venda = new VendaModel();
		
		
		while (produtoModel.getId()!=null) {
			 produto = produtoRepository.getById(produtoModel.getId());
			 listaproduto.add(produto);
			
		       if(produtoModel.getId()!=null) {
		    	   produto.setQuantidade_vendido(produtoModel.getQuantidade_vendido());
		    	   listaparalela.add(produto);
		    	 double valor=0;
		    	 double total=0;
		    	 valor=+(produto.getPreco()*produto.getQuantidade_vendido());
		    	valorTotal.add(valor);		
		    	
                for(Double t : valorTotal ) {
                	total += t.doubleValue();                }
                val = total;
		    	andView.addObject("valorTotal",total );
		   		andView.addObject("listaprodutos", listaparalela);
		   		andView.addObject("username", getUsuarioLogado());
		    	   return andView;
		       }
		}
		while(produtoModel.getId()==null) {
			venda.setTotal(val);
			venda.setData(new Date());
			venda.setFormaPagamento(formaPagamento);
			venda.setListaproduto(listaproduto);
			 venda = vendaRepository.saveAndFlush(venda);
			 
			 for(ProdutoModel pro : listaparalela) {
				 ProdutoId produtoid = new ProdutoId();
				 produtoid.setIdProduto(pro.getId());
				 produtoid.setIdVenda(venda.getId());
				 produtoid.setQunatidadeProdutoVendido(pro.getQuantidade_vendido());
				 produtoidrepository.save(produtoid);
			 }
			 
			if(produtoModel.getId()==null) {
				
				    listaproduto.clear();
					listaparalela.clear();
					valorTotal.clear();
					if(informacliente.equalsIgnoreCase("true")) {
						List<ClienteModel> listaCliente = new ArrayList<>();
						listaCliente = clienteRepository.findAll();
						paginacliente.addObject("listaCliente", listaCliente);
						paginacliente.addObject("venda", venda);
						paginacliente.addObject("username", getUsuarioLogado());
						return paginacliente;
					}
				andView.addObject("username", getUsuarioLogado());
		    	   return andView;
				   
		       }
	 	}
		return andView;
	}
	
	@GetMapping(value = "/selecionarcliente/selecionavenda/{idcliente}/{idvenda}")
	public ModelAndView selecionacliente(@PathVariable("idcliente") Long idcliente,
			@PathVariable("idvenda") Long idvenda) {
		
		
       ClienteModel cliente = clienteRepository.buscarClientePorId(idcliente);
		vendaRepository.insereclienteAVenda(cliente, idvenda);
		ModelAndView andView = new ModelAndView("/caixaTemp/caixavenda");
		andView.addObject("username", getUsuarioLogado());
		return andView;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/listavenda")
	public ModelAndView listadeVendas() {
		ModelAndView modelview = new ModelAndView("/relatoriosTemp/listavenda");
		List<VendaModel> listavenda = new ArrayList<VendaModel>();
		listavenda = vendaRepository.listaVenda();
		modelview.addObject("listavenda", listavenda);
		modelview.addObject("username", getUsuarioLogado());
		return modelview;
	}
	
	
	@GetMapping("/actionVisualizarVenda/{idvenda}")
	public ModelAndView visualizarVenda(@PathVariable("idvenda") Long idvenda) {
		ModelAndView modelview = new ModelAndView("/relatoriosTemp/visualizarvenda");
		VendaModel visulVenda = vendaRepository.buscarVendaPorId(idvenda);
		
		
		
		 modelview.addObject("listaVisualizarVenda", visulVenda.getListaproduto());
		
		modelview.addObject("clientevenda", visulVenda.getClienteModel());
		return modelview;
	}
	
	
	
	
}

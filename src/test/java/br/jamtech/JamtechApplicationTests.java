package br.jamtech;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jamtech.model.ClienteModel;
import br.jamtech.model.ProdutoId;
import br.jamtech.model.ProdutoModel;
import br.jamtech.model.UsuarioModel;
import br.jamtech.model.VendaModel;
import br.jamtech.repository.ClienteRepository;
import br.jamtech.repository.ProdutoRepository;
import br.jamtech.repository.UsuarioRepository;
import br.jamtech.repository.VendaRepository;
import br.jamtech.repository.produtoIdRepository;

@SpringBootTest
class JamtechApplicationTests {

	@Autowired
	private ProdutoRepository pr;

	@Autowired
	private VendaRepository vendaRepository;

	private UsuarioRepository UsuarioRepository;
	
	@Autowired 
	private produtoIdRepository produtoidrepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void addproduto() {
		ProdutoModel pd = new ProdutoModel();
		pd.setCodigo_barra(123);
		pd.setDescricao("Fonte");
		pd.setPreco(50.00);
		pd.setPreco_custo(25.00);
		pd.setQuantidade(2);
		
		pr.save(pd);
	}
	

	@Test
	public void addCliente() {
		ClienteModel cliente = new ClienteModel();
		cliente.setCpf_cnpj("55566644412");
		cliente.setRazao_social("Tech");
		cliente.setNome_fantasia("nome_fantasia");
		cliente.setNome_cliente("nome_cliente");
		cliente.setEmail("email");
		clienteRepository.save(cliente);
	}

	@Test
	public void addUsuario() {
		UsuarioModel usuario = new UsuarioModel();
		usuario.setEmailUsu("emailUsu");
		usuario.setIdadeUsu(28);
		usuario.setLoginUsu("login");
		usuario.setNomeUsu("nome");
		usuario.setRepitaSenhaUsu("rep");
		usuario.setSobrenomeUsu("asd");
	}

	@Test
	public void addVenda() {

		  List<ProdutoModel> listaproduto = new ArrayList<ProdutoModel>();
		  
		  ProdutoModel produto1 = pr.buscarProdutoPorDescricao2("Gabinete");
		  ProdutoModel produto2 = pr.buscarProdutoPorDescricao2("Mouse");
		  ProdutoModel produto3 = pr.buscarProdutoPorDescricao2("Fonte");
		  
		  
		  
		  listaproduto.add(produto1);
		  listaproduto.add(produto2);
		  listaproduto.add(produto3);
		  
		  
		  VendaModel venda = new VendaModel(); 
		  venda.setFormaPagamento("Debito");
		  venda.setListaproduto(listaproduto);
		  
		  
		  venda = vendaRepository.saveAndFlush(venda);
		  
		  
		  
		  System.out.println(venda.getId());
		  ProdutoId prId = new ProdutoId();
		  prId.setIdVenda(venda.getId());
		  prId.setIdProduto(produto1.getId());
		  prId.setQunatidadeProdutoVendido(3);
		  produtoidrepository.save(prId);
	}
	
	
	@Test
	public void buscarProduto() {
		List<ProdutoModel> listaproduto = pr.buscarListaProdutoPorDescricao("Gabinete");

		for(ProdutoModel p : listaproduto) {
			System.out.println(p.getDescricao());
		}
	}

	
	@Test
	public void listaVenda() {
		
		List<VendaModel> listavenda = new ArrayList<VendaModel>();
		listavenda = vendaRepository.listaVenda();
		
		for(VendaModel v : listavenda) {
			System.out.println(v.getId());
		}
		
	}
	@Test
	public void addclienteVenda(){
		
		ClienteModel cliente = clienteRepository.buscarClientePorId(110L);
		
		vendaRepository.insereclienteAVenda(cliente, 133L);
		//vendaRepository.inseretipoVenda("pix", 133L);
	}
	
	
	
}

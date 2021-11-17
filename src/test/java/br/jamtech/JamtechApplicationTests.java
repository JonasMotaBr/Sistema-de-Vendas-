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
		pd.setCodigo_barra(2134513215);
		pd.setDescricao("Fonte 500W");
		pd.setPreco(230.00);
		pd.setPreco_custo(100.00);
		pd.setQuantidade(20);
		ProdutoModel pd2 = new ProdutoModel();
		pd2.setCodigo_barra(123);
		pd2.setDescricao("Gabinete Corsair");
		pd2.setPreco(399.00);
		pd2.setPreco_custo(180.00);
		pd2.setQuantidade(8);
		ProdutoModel pd3 = new ProdutoModel();
		pd3.setCodigo_barra(123);
		pd3.setDescricao("Gabinete Bluecase");
		pd3.setPreco(250.00);
		pd3.setPreco_custo(125.00);
		pd3.setQuantidade(6);
		ProdutoModel pd4 = new ProdutoModel();
		pd4.setCodigo_barra(123);
		pd4.setDescricao("Mouse Corsair");
		pd4.setPreco(250.00);
		pd4.setPreco_custo(100.00);
		pd4.setQuantidade(4);
		ProdutoModel pd5 = new ProdutoModel();
		pd5.setCodigo_barra(123);
		pd5.setDescricao("Cooler RGB");
		pd5.setPreco(50.00);
		pd5.setPreco_custo(20.00);
		pd5.setQuantidade(19);
		
		pr.save(pd);
		pr.save(pd2);
		pr.save(pd3);
		pr.save(pd4);
		pr.save(pd5);
	}
	

	@Test
	public void addCliente() {
		ClienteModel cliente = new ClienteModel();
		cliente.setCpf_cnpj("55566644412");
		cliente.setRazao_social("Lojao Eletronicos");
		cliente.setNome_fantasia("Lojao Eletronicos");
		cliente.setNome_cliente("");
		cliente.setEmail("lojao@gmail.com");
		ClienteModel cliente2 = new ClienteModel();
		cliente2.setCpf_cnpj("0642124842");
		cliente2.setRazao_social("");
		cliente2.setNome_fantasia("");
		cliente2.setNome_cliente("Renata");
		cliente2.setEmail("renata@gmail.com");
		ClienteModel cliente3 = new ClienteModel();
		cliente3.setCpf_cnpj("06846120214");
		cliente3.setRazao_social("");
		cliente3.setNome_fantasia("");
		cliente3.setNome_cliente("Clara");
		cliente3.setEmail("clara@gmail.com");
		clienteRepository.save(cliente);
		clienteRepository.save(cliente2);
		clienteRepository.save(cliente3);
	}

	@Test
	public void addUsuario() {

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

package br.jamtech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.jamtech.model.ClienteModel;
import br.jamtech.model.VendaModel;


@Repository
@Transactional
public interface VendaRepository extends JpaRepository<VendaModel, Long> {

	
	@Query("select p from VendaModel p where p.id = ?1")
	VendaModel buscarVendaPorId(Long id);
	
	@Modifying
	@Query("update VendaModel v set v.clienteModel = ?1 where v.id = ?2")
	void insereclienteAVenda(ClienteModel cliente, Long idvenda);
	
	@Query("update VendaModel v set v.formaPagamento = ?1 where v.id = ?2")
	void inseretipoVenda(String formapag, Long idvenda);
	
	
	@Query("select p from VendaModel p ")
	List<VendaModel> listaVenda();
}

package br.jamtech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.jamtech.model.ProdutoId;
import br.jamtech.model.ProdutoModel;

@Repository
@Transactional
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

	
	@Query("select p from ProdutoModel p where p.descricao like lower(CONCAT('%',:descricao,'%'))")
	ProdutoModel buscarProdutoPorDescricao(String descricao);
	

	@Query("select p from ProdutoModel p where lower(p.descricao) like lower(concat('%', ?1,'%'))")
	List<ProdutoModel> buscarListaProdutoPorDescricao(String descricao);
	
	@Query("select p from ProdutoModel p where p.descricao = ?1")
	ProdutoModel buscarProdutoPorDescricao2(String descricao);
	
}

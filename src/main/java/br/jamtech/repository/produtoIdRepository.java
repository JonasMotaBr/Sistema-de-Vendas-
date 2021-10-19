package br.jamtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.jamtech.model.ProdutoId;

public interface produtoIdRepository extends JpaRepository<ProdutoId, Long> {
	
	
	@Query("select p from ProdutoId p where p.id = ?1")
	ProdutoId buscarid(Long id);

}

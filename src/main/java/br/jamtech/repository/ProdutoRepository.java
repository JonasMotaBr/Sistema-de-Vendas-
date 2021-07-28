package br.jamtech.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.jamtech.model.ProdutoModel;

@Repository
@Transactional
public interface ProdutoRepository extends CrudRepository<ProdutoModel, Long> {

}

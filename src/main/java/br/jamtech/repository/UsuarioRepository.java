package br.jamtech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.jamtech.model.UsuarioModel;


@Repository
@Transactional
public interface UsuarioRepository  extends CrudRepository<UsuarioModel, Long>{

	@Query("select p from UsuarioModel p where p.nomeUsu like %?1%")
    List<UsuarioModel> buscarUsuarioPorNome(String nomeUsu);
	
}

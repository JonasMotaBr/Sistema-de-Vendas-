package br.jamtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.jamtech.model.ClienteModel;
import br.jamtech.model.VendaModel;


@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

	
	
	
	@Query("select p from ClienteModel p where p.id = ?1")
	ClienteModel buscarClientePorId(Long id);
}

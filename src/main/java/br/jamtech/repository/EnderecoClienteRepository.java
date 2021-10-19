package br.jamtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.jamtech.model.EnderecoClienteModel;

@Repository
@Transactional
public interface EnderecoClienteRepository extends JpaRepository<EnderecoClienteModel, Long> {

}

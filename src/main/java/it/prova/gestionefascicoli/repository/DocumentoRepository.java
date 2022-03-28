package it.prova.gestionefascicoli.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionefascicoli.model.Documento;

public interface DocumentoRepository
		extends PagingAndSortingRepository<Documento, Long>, JpaSpecificationExecutor<Documento> {
	@Query("from Documento d left join fetch d.fascicolo f where d.id = ?1")
	public Optional<Documento> findByIdEager(Long idDocumento);
}

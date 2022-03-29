package it.prova.gestionefascicoli.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionefascicoli.model.Fascicolo;

public interface FascicoloRepository
		extends PagingAndSortingRepository<Fascicolo, Long>, JpaSpecificationExecutor<Fascicolo> {
	@Query("from Fascicolo f left join fetch f.documenti d where f.id = ?1")
	public Optional<Fascicolo> findByIdEager(Long idFascicolo);

	@Query("from Fascicolo f where f.codice like %?1%")
	List<Fascicolo> findByCodice(String codice);
}

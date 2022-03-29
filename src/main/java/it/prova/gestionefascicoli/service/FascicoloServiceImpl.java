package it.prova.gestionefascicoli.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionefascicoli.exceptions.FascicoloConDocumentiException;
import it.prova.gestionefascicoli.exceptions.FascicoloNotFoundException;
import it.prova.gestionefascicoli.model.Fascicolo;
import it.prova.gestionefascicoli.repository.FascicoloRepository;

@Service
public class FascicoloServiceImpl implements FascicoloService {

	@Autowired
	private FascicoloRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Fascicolo> listAllElements() {
		return (List<Fascicolo>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Fascicolo caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Fascicolo fascicoloInstance) {
		Fascicolo fascicoloReloaded = repository.findById(fascicoloInstance.getId()).orElse(null);
		if (fascicoloReloaded == null || fascicoloReloaded.getId() == null) {
			throw new NullPointerException();
		}
		fascicoloReloaded.setCodice(fascicoloInstance.getCodice());
		fascicoloReloaded.setDescrizione(fascicoloInstance.getDescrizione());
		fascicoloReloaded.setDataChiusura(fascicoloInstance.getDataChiusura());
		repository.save(fascicoloReloaded);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Fascicolo fascicoloInstance) {
		if (fascicoloInstance == null || fascicoloInstance.getId() != null) {
			throw new NullPointerException();
		}

		fascicoloInstance.setDataCreazione(new Date());
		fascicoloInstance.setDataChiusura(new Date());
		repository.save(fascicoloInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Fascicolo fascicoloInstance) {
		repository.delete(fascicoloInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Fascicolo> findByExample(Fascicolo example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Fascicolo> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getCodice()))
				predicates.add(cb.like(cb.upper(root.get("codice")), "%" + example.getCodice().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getDescrizione()))
				predicates.add(
						cb.like(cb.upper(root.get("descrizione")), "%" + example.getDescrizione().toUpperCase() + "%"));

			if (example.getDataCreazione() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataCreazione"), example.getDataCreazione()));

			if (example.getDataChiusura() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataChiusura"), example.getDataChiusura()));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = null;
		// se non passo parametri di paginazione non ne tengo conto
		if (pageSize == null || pageSize < 10)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}

	@Override
	public Fascicolo caricaSingoloElementoEager(Long id) {
		return repository.findByIdEager(id).orElse(null);
	}

	@Override
	public void inserisciFascicoloConDate(Fascicolo fascicoloInstance) {
		fascicoloInstance.setDataCreazione(new Date());
		fascicoloInstance.setDataChiusura(new Date());
		repository.save(fascicoloInstance);
	}

	@Override
	public void rimuoviFascicolo(Long id) {
		Fascicolo toRemove = repository.findByIdEager(id).orElse(null);

		if (toRemove == null) {
			throw new FascicoloNotFoundException("Fascicolo non trovato");
		}

		if (!toRemove.getDocumenti().isEmpty()) {
			throw new FascicoloConDocumentiException("Il fascicolo da rimuovere ha dei documenti al suo interno");
		}

		repository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Fascicolo> cercaByCodiceILike(String term) {
		return repository.findByCodice(term);
	}

}

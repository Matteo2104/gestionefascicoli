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

import it.prova.gestionefascicoli.model.Fascicolo;
import it.prova.gestionefascicoli.repository.FascicoloRepository;

@Service
public class FascicoloServiceImpl implements FascicoloService {

	@Autowired
	private FascicoloRepository repository;
	
	@Override
	@Transactional
	public List<Fascicolo> cercaByCodiceILike(String term) {
		return repository.findByCodiceIgnoreCaseContaining(term);
	}

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
		repository.save(fascicoloInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Fascicolo fascicoloInstance) {
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

			if (example.getDataUltimaModifica() != null)
				predicates
						.add(cb.greaterThanOrEqualTo(root.get("dataUltimaModifica"), example.getDataUltimaModifica()));

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
		fascicoloInstance.setDataUltimaModifica(new Date());
		repository.save(fascicoloInstance);
	}

}

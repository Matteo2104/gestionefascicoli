package it.prova.gestionefascicoli.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionefascicoli.model.Documento;
import it.prova.gestionefascicoli.repository.DocumentoRepository;

public class DocumentoServiceImpl implements DocumentoService {

	@Autowired
	private DocumentoRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Documento> listAllElements() {
		return (List<Documento>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Documento caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Documento documentoInstance) {
		repository.save(documentoInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Documento documentoInstance) {
		repository.save(documentoInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Documento documentoInstance) {
		repository.delete(documentoInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Documento> findByExample(Documento example, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Documento> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (StringUtils.isNotEmpty(example.getCodice()))
				predicates.add(cb.like(cb.upper(root.get("codice")), "%" + example.getCodice().toUpperCase() + "%"));

			if (StringUtils.isNotEmpty(example.getDescrizione()))
				predicates.add(
						cb.like(cb.upper(root.get("descrizione")), "%" + example.getDescrizione().toUpperCase() + "%"));

			if (example.getRiservato() != null)
				predicates.add(cb.equal(root.get("riservato"), example.getRiservato()));

			if (example.getDataCreazione() != null)
				predicates.add(cb.greaterThanOrEqualTo(root.get("dataCreazione"), example.getDataCreazione()));

			if (example.getDataUltimaModifica() != null)
				predicates
						.add(cb.greaterThanOrEqualTo(root.get("dataUltimaModifica"), example.getDataUltimaModifica()));

			if (example.getFascicolo() != null && example.getFascicolo().getId() != null) 
				predicates.add(cb.equal(cb.upper(root.get("fascicolo")), example.getFascicolo().getId()));

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
	public Documento caricaSingoloElementoEager(Long id) {
		return repository.findByIdEager(id).orElse(null);
	}

}

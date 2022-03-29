package it.prova.gestionefascicoli.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionefascicoli.model.Documento;

public interface DocumentoService {
	public List<Documento> listAllElements();

	public Documento caricaSingoloElemento(Long id);

	public Documento caricaSingoloElementoEager(Long id);

	public void aggiorna(Documento documentoInstance);

	public void inserisciNuovo(Documento documentoInstance);

	public void rimuovi(Documento documentoInstance);

	public Page<Documento> findByExample(Documento example, Integer pageNo, Integer pageSize, String sortBy);

	public void inserisciNuovoConDate(Documento documentoInstance);

}

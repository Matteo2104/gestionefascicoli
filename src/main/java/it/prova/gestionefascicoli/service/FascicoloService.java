package it.prova.gestionefascicoli.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionefascicoli.model.Fascicolo;

public interface FascicoloService {
	public List<Fascicolo> listAllElements();

	public Fascicolo caricaSingoloElemento(Long id);

	public Fascicolo caricaSingoloElementoEager(Long id);

	public void aggiorna(Fascicolo fascicoloInstance);

	public void inserisciNuovo(Fascicolo fascicoloInstance);

	public void rimuovi(Fascicolo fascicoloInstance);

	public Page<Fascicolo> findByExample(Fascicolo example, Integer pageNo, Integer pageSize, String sortBy);


	public List<Fascicolo> cercaByCodiceILike(String term);


	public void inserisciFascicoloConDate(Fascicolo fascicoloInstance);


	public void rimuoviFascicolo(Long id);

}

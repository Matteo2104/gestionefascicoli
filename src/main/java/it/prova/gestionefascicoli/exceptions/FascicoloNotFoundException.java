package it.prova.gestionefascicoli.exceptions;

public class FascicoloNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FascicoloNotFoundException(String message) {
		super(message);
	}
}

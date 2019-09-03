package br.com.fontedeestudo.cursoparaestudo.services.exceptions;


// classe de exceção personalizada para quando objetos não forem encontrado

public class DataIntegrityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	

	public DataIntegrityException(String message) {
		super(message);

	}

	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
		
	}
	
	

}

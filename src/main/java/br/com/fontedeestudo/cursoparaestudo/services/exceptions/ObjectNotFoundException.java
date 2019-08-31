package br.com.fontedeestudo.cursoparaestudo.services.exceptions;


// classe de exceção personalizada para quando objetos não forem encontrado

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	

	public ObjectNotFoundException(String message) {
		super(message);

	}

	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}
	
	

}

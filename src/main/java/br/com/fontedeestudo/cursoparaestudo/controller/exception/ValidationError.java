package br.com.fontedeestudo.cursoparaestudo.controller.exception;

import java.util.ArrayList;
import java.util.List;

//Classe que contem o objeto de erro(standardError) e lista de objeto de erro de validação dos campos(FieldMenssage)

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> listaErros = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
	super(status, msg, timeStamp);
	
	}

	public List<FieldMessage> getlistaErros() {
		return listaErros;
	}

	public void addError(String fieldName, String menssage) {
		listaErros.add(new FieldMessage(fieldName, menssage));
	}
	
	

}

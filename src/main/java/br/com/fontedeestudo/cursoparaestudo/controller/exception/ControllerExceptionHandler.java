package br.com.fontedeestudo.cursoparaestudo.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.fontedeestudo.cursoparaestudo.services.exceptions.DataIntegrityException;
import br.com.fontedeestudo.cursoparaestudo.services.exceptions.ObjectNotFoundException;

// classe para interceptar alguma exceção de erro nos controllers 

@ControllerAdvice
public class ControllerExceptionHandler {
	
	// caso ocorra o erro de não encontrar, gera essa execao personalizada
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	// caso ocorra o erro de excluir categoria com produtos, gera essa execao personalizada
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError>DataIntegrityException(ObjectNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	// erro de validação 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError>Valid(MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
		
		//pegando cada erro na lista de erros gerado pela exceção e inserindo na lista de validationError
		for(FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
	

}

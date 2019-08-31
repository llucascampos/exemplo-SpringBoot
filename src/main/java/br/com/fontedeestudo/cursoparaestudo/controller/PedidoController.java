package br.com.fontedeestudo.cursoparaestudo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fontedeestudo.cursoparaestudo.domain.Pedido;
import br.com.fontedeestudo.cursoparaestudo.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService catService;
	
	// metodo que recebe o id na URL e passa para a função no service e retorna uma resposta HTTP  
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable  Integer id) {
		Pedido obj = catService.find(id);
			
	    
		return ResponseEntity.ok().body(obj);
	}
}

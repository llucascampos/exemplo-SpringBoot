package br.com.fontedeestudo.cursoparaestudo.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fontedeestudo.cursoparaestudo.domain.Categoria;
import br.com.fontedeestudo.cursoparaestudo.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaService catService;
	
	// metodo que recebe o id na URL e passa para a função no service e retorna uma resposta HTTP  
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable  Integer id) {
		Categoria obj = catService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public  ResponseEntity<Object> insert(@RequestBody Categoria obj){
		obj = catService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Categoria> update(@RequestBody Categoria obj, @PathVariable Integer id){
		obj.setId(id);
		obj = catService.update(obj);
		return ResponseEntity.noContent().build();
	}
}

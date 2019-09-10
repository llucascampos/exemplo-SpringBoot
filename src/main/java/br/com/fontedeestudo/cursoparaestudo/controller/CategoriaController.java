package br.com.fontedeestudo.cursoparaestudo.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fontedeestudo.cursoparaestudo.domain.Categoria;
import br.com.fontedeestudo.cursoparaestudo.dto.CategoriaDTO;
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
	public  ResponseEntity<Object> insert(@Valid @RequestBody CategoriaDTO objDto){  //@Valid para fazer a validação incluida na classe dto
		Categoria obj = catService.fromDto(objDto);  //convertendo categoria obj para objeto da entidade atravez do metodo criado na camada de serviço
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
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable  Integer id) {
		catService.delete(id);
		return ResponseEntity.noContent().build() ;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> lista = catService.findAll();
		List<CategoriaDTO> listaDto = lista.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); // coonvertendo lista de categorias para uma lista de categoriasDTO apenas com os dados que quero
		return ResponseEntity.ok().body(listaDto);
	}
	
	//pegando com paginação, pegar valor via parametro na url (http://localhost:8080/categorias/page?linesPerPage=3&nPage=2) se nao for passado pega o valor default
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value="nPage", defaultValue="0")Integer nPage, 
													   @RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
													   @RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
													   @RequestParam(value="direction", defaultValue="ASC")String direction) {
		Page<Categoria> lista = catService.findPage(nPage, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listaDto = lista.map(obj -> new CategoriaDTO(obj)); // coonvertendo lista paginada de categorias para uma lista de categoriasDTO apenas com os dados que quero
		return ResponseEntity.ok().body(listaDto);
	}
	
	
}

package br.com.fontedeestudo.cursoparaestudo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fontedeestudo.cursoparaestudo.controller.utils.URL;
import br.com.fontedeestudo.cursoparaestudo.domain.Produto;
import br.com.fontedeestudo.cursoparaestudo.dto.CategoriaDTO;
import br.com.fontedeestudo.cursoparaestudo.dto.ProdutoDTO;
import br.com.fontedeestudo.cursoparaestudo.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoController {
	
	@Autowired
	ProdutoService prodService;
	
	// metodo que recebe o id na URL e passa para a função no service e retorna uma resposta HTTP  
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable  Integer id) {
		Produto obj = prodService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//pegando com paginação, pegar valor via parametro na url (http://localhost:8080/categorias/page?linesPerPage=3&nPage=2) se nao for passado pega o valor default
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<Page<ProdutoDTO>> findPage(
				@RequestParam(value="nome", defaultValue="")String nome, 
				@RequestParam(value="categorias", defaultValue="")String categorias, 
				@RequestParam(value="nPage", defaultValue="0")Integer nPage, 
				@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
				@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
				@RequestParam(value="direction", defaultValue="ASC")String direction) {
			
			String nomeDecoded = URL.decodeParam(nome);
			List<Integer> ids = URL.decodeIntList(categorias); 
			Page<Produto> lista = prodService.search(nomeDecoded ,ids, nPage, linesPerPage, orderBy, direction);
			Page<ProdutoDTO> listaDto = lista.map(obj -> new ProdutoDTO(obj)); // coonvertendo lista paginada de categorias para uma lista de categoriasDTO apenas com os dados que quero
			return ResponseEntity.ok().body(listaDto);
		}
}

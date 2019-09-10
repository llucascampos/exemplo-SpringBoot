package br.com.fontedeestudo.cursoparaestudo.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.fontedeestudo.cursoparaestudo.domain.Cliente;
import br.com.fontedeestudo.cursoparaestudo.dto.ClienteDTO;
import br.com.fontedeestudo.cursoparaestudo.dto.ClienteNewDTO;
import br.com.fontedeestudo.cursoparaestudo.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {
	
	@Autowired
	ClienteService cliService;
	
	// metodo que recebe o id na URL e passa para a função no service e retorna uma resposta HTTP  
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable  Integer id) {
		Cliente obj = cliService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Cliente> update(@RequestBody Cliente obj, @PathVariable Integer id){
		obj.setId(id);
		obj = cliService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable  Integer id) {
		cliService.delete(id);
		return ResponseEntity.noContent().build() ;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> lista = cliService.findAll();
		List<ClienteDTO> listaDto = lista.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList()); // coonvertendo lista de categorias para uma lista de categoriasDTO apenas com os dados que quero
		return ResponseEntity.ok().body(listaDto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public  ResponseEntity<Object> insert(@Valid @RequestBody ClienteNewDTO objDto){  //@Valid para fazer a validação incluida na classe dto
		Cliente obj = cliService.fromDto(objDto);  //convertendo categoria obj para objeto da entidade atravez do metodo criado na camada de serviço
		obj = cliService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}

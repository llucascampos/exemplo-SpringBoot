package br.com.fontedeestudo.cursoparaestudo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.fontedeestudo.cursoparaestudo.domain.Cliente;
import br.com.fontedeestudo.cursoparaestudo.domain.Cliente;
import br.com.fontedeestudo.cursoparaestudo.dto.ClienteDTO;
import br.com.fontedeestudo.cursoparaestudo.repositories.ClienteRepository;
import br.com.fontedeestudo.cursoparaestudo.services.exceptions.DataIntegrityException;
import br.com.fontedeestudo.cursoparaestudo.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado ID" + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente obj) { 
		// procurando o cliente que vai ser atualizado pelo id que chegou como parametro
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj); 
		return repository.save(newObj);
	}
	
	public void delete(Integer id) {
		
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel clientes que tem vinculo");
		}
	}
	
	public List<Cliente> findAll(){
		List<Cliente> lista = repository.findAll();
		return lista;
	}
	
	// metodo para pegar categorias paginadas passando numero de paginas, quantidade por pagina,qual campo quero ordenar e direção
	public Page<Cliente> findPage(Integer nPage, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(nPage, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repository.findAll(pageRequest);
	}
	
	
	//metodo para converter DTO para objeto da entidade categoria
	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	//private porque é um metodo auxiliar de dentro da classe mesmo, nao sera usado fora
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}

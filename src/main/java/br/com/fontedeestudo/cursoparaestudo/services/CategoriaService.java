package br.com.fontedeestudo.cursoparaestudo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.fontedeestudo.cursoparaestudo.domain.Categoria;
import br.com.fontedeestudo.cursoparaestudo.dto.CategoriaDTO;
import br.com.fontedeestudo.cursoparaestudo.repositories.CategoriaRepository;
import br.com.fontedeestudo.cursoparaestudo.services.exceptions.DataIntegrityException;
import br.com.fontedeestudo.cursoparaestudo.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository repository;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado ID" + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repository.save(obj);
	}
	
	public void delete(Integer id) {
		
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
			
		}
		
	}
	
	public List<Categoria> findAll(){
		List<Categoria> lista = repository.findAll();
		return lista;
	}
	
	// metodo para pegar categorias paginadas passando numero de paginas, quantidade por pagina,qual campo quero ordenar e direção
	public Page<Categoria> findPage(Integer nPage, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(nPage, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repository.findAll(pageRequest);
	}
	
	
	//metodo para converter DTO para objeto da entidade categoria
	public Categoria fromDto(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
		
}

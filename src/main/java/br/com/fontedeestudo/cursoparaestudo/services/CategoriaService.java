package br.com.fontedeestudo.cursoparaestudo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fontedeestudo.cursoparaestudo.domain.Categoria;
import br.com.fontedeestudo.cursoparaestudo.repositories.CategoriaRepository;
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
}

package br.com.fontedeestudo.cursoparaestudo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.fontedeestudo.cursoparaestudo.domain.Categoria;
import br.com.fontedeestudo.cursoparaestudo.domain.Produto;
import br.com.fontedeestudo.cursoparaestudo.repositories.CategoriaRepository;
import br.com.fontedeestudo.cursoparaestudo.repositories.ProdutoRepository;
import br.com.fontedeestudo.cursoparaestudo.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository repository;
	
	@Autowired
	CategoriaRepository catRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repository.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado ID" + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer nPage, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(nPage, linesPerPage, Direction.valueOf(direction) , orderBy);
		List<Categoria> categorias = catRepository.findAllById(ids);
		return repository.search(nome, categorias, pageRequest);
	}
}

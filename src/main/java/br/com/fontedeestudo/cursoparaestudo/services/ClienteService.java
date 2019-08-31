package br.com.fontedeestudo.cursoparaestudo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fontedeestudo.cursoparaestudo.domain.Categoria;
import br.com.fontedeestudo.cursoparaestudo.domain.Cliente;
import br.com.fontedeestudo.cursoparaestudo.repositories.ClienteRepository;
import br.com.fontedeestudo.cursoparaestudo.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado ID" + id + ", Tipo: " + Cliente.class.getName()));
	}
}

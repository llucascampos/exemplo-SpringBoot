package br.com.fontedeestudo.cursoparaestudo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fontedeestudo.cursoparaestudo.domain.Pedido;
import br.com.fontedeestudo.cursoparaestudo.repositories.PedidoRepository;
import br.com.fontedeestudo.cursoparaestudo.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRepository repository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado ID" + id + ", Tipo: " + Pedido.class.getName()));
	}
}

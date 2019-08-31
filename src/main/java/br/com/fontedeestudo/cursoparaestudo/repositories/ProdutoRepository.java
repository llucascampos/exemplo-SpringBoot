package br.com.fontedeestudo.cursoparaestudo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fontedeestudo.cursoparaestudo.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
			
	
}

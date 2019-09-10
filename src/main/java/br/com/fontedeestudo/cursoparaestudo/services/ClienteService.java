package br.com.fontedeestudo.cursoparaestudo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fontedeestudo.cursoparaestudo.domain.Cidade;
import br.com.fontedeestudo.cursoparaestudo.domain.Cliente;
import br.com.fontedeestudo.cursoparaestudo.domain.Endereco;
import br.com.fontedeestudo.cursoparaestudo.domain.enums.TipoCliente;
import br.com.fontedeestudo.cursoparaestudo.dto.ClienteDTO;
import br.com.fontedeestudo.cursoparaestudo.dto.ClienteNewDTO;
import br.com.fontedeestudo.cursoparaestudo.repositories.CidadeRepository;
import br.com.fontedeestudo.cursoparaestudo.repositories.ClienteRepository;
import br.com.fontedeestudo.cursoparaestudo.services.exceptions.DataIntegrityException;
import br.com.fontedeestudo.cursoparaestudo.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repository;
	CidadeRepository cidRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		
		return obj.orElseThrow( () -> new ObjectNotFoundException("Objeto não encontrado ID" + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repository.save(obj);
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
	
	
	//metodo para converter DTO para objeto da entidade categoria / usado para update
	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	//usado para inserir novo cliente
	public Cliente fromDto(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}if(objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		
		return cli;
	}
	
	//private porque é um metodo auxiliar de dentro da classe mesmo, nao sera usado fora
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}

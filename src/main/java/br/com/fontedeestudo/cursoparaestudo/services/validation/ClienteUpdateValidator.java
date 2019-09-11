package br.com.fontedeestudo.cursoparaestudo.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.fontedeestudo.cursoparaestudo.controller.exception.FieldMessage;
import br.com.fontedeestudo.cursoparaestudo.domain.Cliente;
import br.com.fontedeestudo.cursoparaestudo.dto.ClienteDTO;
import br.com.fontedeestudo.cursoparaestudo.repositories.ClienteRepository;

// classe para criar validação personalida para validar tipo de pessoa (classe da validação para a anotação)
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request; // tem uma função que permiti obter o parametro passado na URL
	
	@Autowired
	private ClienteRepository cliRepository;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	//metodo isValid ira validar o objeto passsado como parametro, ele deve retornar true se for valido
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map =  (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); // para pegar o id que vem na URL
		Integer urlId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();  // instanciando o objeto criado para receber o nome do campo e a mensagem que esta invalida

		// Só permiti atualizar se o usuario estiver atualizando o seu proprio email
		Cliente aux = cliRepository.findByEmail(objDto.getEmail());
		if(aux != null && !aux.getId().equals(urlId)) {
			list.add(new FieldMessage("email ", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

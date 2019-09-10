package br.com.fontedeestudo.cursoparaestudo.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.fontedeestudo.cursoparaestudo.controller.exception.FieldMessage;
import br.com.fontedeestudo.cursoparaestudo.domain.enums.TipoCliente;
import br.com.fontedeestudo.cursoparaestudo.dto.ClienteNewDTO;
import br.com.fontedeestudo.cursoparaestudo.services.validation.utils.BR;

// classe para criar validação personalida para validar tipo de pessoa 
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	//metodo isValid ira validar o objeto passsado como parametro, ele deve retornar true se for valido
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();  // instanciando o objeto criado para receber o nome do campo e a mensagem que esta invalida

			//se o for pessoa fisica e não for valido o "isValidCNPJ
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Invalido"));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Invalido"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}

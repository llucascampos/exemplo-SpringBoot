package br.com.fontedeestudo.cursoparaestudo.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.fontedeestudo.cursoparaestudo.services.validation.ClienteInsert;

@ClienteInsert  // anotação criada para validação personalizada do tipo de cliente	
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@NotEmpty(message = "Campo obrigatorio")
	@Length(min=3, max=20, message = "minimo de caracter 3, maximo 20")
	private String nome;
	
	@NotEmpty(message = "Campo obrigatorio")
	@Email(message="Email invalido")
	private String email;
	
	@NotEmpty(message = "Campo obrigatorio")
	private String cpfOuCnpj;
	
	private Integer tipo;
	
	@NotEmpty(message = "Campo obrigatorio")
	private String logradouro;
	@NotEmpty(message = "Campo obrigatorio")
	private String numero;
	
	private String complemento;
	@NotEmpty(message = "Campo obrigatorio")
	private String bairro;
	
	@NotEmpty(message = "Campo obrigatorio")
	private String cep;
	
	@NotEmpty(message = "Campo obrigatorio")
	private String telefone1;
	
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeId; // apenas o ID da cidade
	
	public ClienteNewDTO() {
		
	}

	public ClienteNewDTO(String nome, String email, String cpfOuCnpj, Integer tipo, String logradouro, String numero,
			String complemento, String bairro, String cep, String telefone1, String telefone2, String telefone3,
			Integer cidadeId) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
		this.cidadeId = cidadeId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	
	
	
}

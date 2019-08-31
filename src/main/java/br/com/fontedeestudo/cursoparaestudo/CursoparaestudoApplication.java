package br.com.fontedeestudo.cursoparaestudo;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fontedeestudo.cursoparaestudo.domain.Categoria;
import br.com.fontedeestudo.cursoparaestudo.domain.Cidade;
import br.com.fontedeestudo.cursoparaestudo.domain.Cliente;
import br.com.fontedeestudo.cursoparaestudo.domain.Endereco;
import br.com.fontedeestudo.cursoparaestudo.domain.Estado;
import br.com.fontedeestudo.cursoparaestudo.domain.ItemPedido;
import br.com.fontedeestudo.cursoparaestudo.domain.Pagamento;
import br.com.fontedeestudo.cursoparaestudo.domain.PagamentoComBoleto;
import br.com.fontedeestudo.cursoparaestudo.domain.PagamentoComCartao;
import br.com.fontedeestudo.cursoparaestudo.domain.Pedido;
import br.com.fontedeestudo.cursoparaestudo.domain.Produto;
import br.com.fontedeestudo.cursoparaestudo.domain.enums.EstadoPagamento;
import br.com.fontedeestudo.cursoparaestudo.domain.enums.TipoCliente;
import br.com.fontedeestudo.cursoparaestudo.repositories.CategoriaRepository;
import br.com.fontedeestudo.cursoparaestudo.repositories.CidadeRepository;
import br.com.fontedeestudo.cursoparaestudo.repositories.ClienteRepository;
import br.com.fontedeestudo.cursoparaestudo.repositories.EnderecoRepository;
import br.com.fontedeestudo.cursoparaestudo.repositories.EstadoRepository;
import br.com.fontedeestudo.cursoparaestudo.repositories.ItemPedidoRepository;
import br.com.fontedeestudo.cursoparaestudo.repositories.PagamentoRepository;
import br.com.fontedeestudo.cursoparaestudo.repositories.PedidoRepository;
import br.com.fontedeestudo.cursoparaestudo.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoparaestudoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursoparaestudoApplication.class, args);
	}
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama Mesa e Banho");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoracao");
		Categoria cat7 = new Categoria(null, "Perfumaria");
	
		
		Produto p1 = new Produto(null, "Computador", 2.000);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
	
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est2);
		Cidade c2 = new Cidade(null, "São Paulo", est1);
		Cidade c3 = new Cidade(null, "Campinas", est1);
		  
		est1.getCidades().addAll(Arrays.asList(c2, c3));
		est2.getCidades().addAll(Arrays.asList(c1));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Lucas", "lucascampos@gmail.com", "08240590", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("951789926"));
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		Endereco e1 = new Endereco(null, "Av coronel pedro dias", "497", "Bloco A 21", "Vila Matilde", "03510010", cli1, c1);
		Endereco e2 = new Endereco(null, "Av moises maimonides", "408", "", "Itaquera", "08240590", cli1, c1);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), e1, cli1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), e2, cli1);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2)); 
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2.000);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 8000.00);
	    
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		
		itemPedidoRepository.saveAll( Arrays.asList(ip1, ip2, ip3));
		
		
	}
}

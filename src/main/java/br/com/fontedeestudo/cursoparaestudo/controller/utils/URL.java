package br.com.fontedeestudo.cursoparaestudo.controller.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static String decodeParam(String s ) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {

			return "";
		}
	}
	
	
	// função que esta sendo usada no produtosController para pegar os ids da categoria passados como argumento na url e convertendo para inteiro
	public static List<Integer> decodeIntList(String s){
		String[] vet = s.split(",");
		
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i<vet.length; i ++) {
		list.add(Integer.parseInt(vet[i]));
		}
		
		return list;
		// mesma coisa usando função labda
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList()));
	}

}

package alpro3.grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AppGrafo2 {

	public static void main(String[] args) {
		Grafo2 grafo = null;

		if (args.length < 1)
			System.out.println("uso: java Grafo0 <arquivo>");
		else {
			grafo = loadFile(args[0]);
			grafo.mostra();

//			System.out.printf("largura(%d): %s\n", 1, grafo.largura(1));
//			System.out.printf("largura(%d): %s\n", 3, grafo.largura(3));
//			System.out.printf("largura(%d): %s\n", 5, grafo.largura(5));
//
//			System.out.printf("\nprofundidade(%d): %s\n", 1,
//					grafo.profundidade(1));
//			System.out.printf("profundidade(%d): %s\n", 3,
//					grafo.profundidade(3));
//			System.out.printf("profundidade(%d): %s\n", 5,
//					grafo.profundidade(5));
//			System.out.printf("Grau de entrada(%d): %s\n", 1, grafo.getGrauEntrada(1));	
//			System.out.printf("Grau de entrada(%d): %s\n", 4, grafo.getGrauEntrada(4));
//			System.out.printf("Grau de saída(%d): %s\n", 1, grafo.getGrauSaida(1));	
//			System.out.printf("Grau de saída(%d): %s\n", 4, grafo.getGrauSaida(4));
			
			System.out.printf("Fonte (%d): %s\n", 1, grafo.isFonte(1));	
			System.out.printf("Fonte (%d): %s\n", 2, grafo.isFonte(2));
			System.out.printf("Sumidouro (%d): %s\n", 1, grafo.isSumidouro(1));	
			System.out.printf("Sumidouro (%d): %s\n", 2, grafo.isSumidouro(2));
			
		}
	}

	public static Grafo2 loadFile(String filename) {
		Grafo2 g = null;
		String linha;

		try {
			BufferedReader buff = new BufferedReader(new FileReader(filename));
			try {
				linha = buff.readLine();
				g = new Grafo2(Integer.parseInt(linha));
				linha = buff.readLine();
				while (linha != null) {
					String[] dados = linha.split(" ");
					g.addEdge(Integer.parseInt(dados[0]),
							Integer.parseInt(dados[1]));
					linha = buff.readLine();
				}
			} finally {
				buff.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return g;
	}
}
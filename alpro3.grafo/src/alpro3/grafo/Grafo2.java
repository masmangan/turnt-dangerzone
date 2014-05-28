package alpro3.grafo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Grafo2 {

	private int[][] adj;
	private boolean visitado[];

	public Grafo2(int nodos) {
		if (nodos < 1) {
			throw new IllegalArgumentException(
					"O nÃºmero de nodos deve ser maior do que zero.");
		}
		adj = new int[nodos + 1][nodos + 1];
		zerarVisitados(nodos);
	}

	private void zerarVisitados(int nodos) {
		visitado = new boolean[nodos + 1];
	}

	public void addEdge(int origem, int destino) {
		if (origem < 1 || origem > adj.length) {
			throw new IllegalArgumentException("Origem inválida!");
		}
		if (destino < 1 || destino > adj.length) {
			throw new IllegalArgumentException("Destino inválido!");
		}
		adj[origem][destino] = 1;
	}

	public void mostra() {
		for (int i = 1; i < adj.length; i++) {
			for (int j = 1; j < adj[i].length; j++) {
				System.out.printf("%d ", adj[i][j]);
			}
			System.out.println();
		}
	}

	public List<Integer> largura(int nodo) {
		checkNodo(nodo);
		zerarVisitados(adj.length);
		List<Integer> resultado = new ArrayList<Integer>();
		Queue<Integer> fila = new LinkedList<>();
		resultado.add(nodo);
		fila.add(nodo);
		while (!fila.isEmpty()) {
			int u = fila.remove();
			for (Integer v : getAdjacentes(u)) {
				if (!visitado[v]) {
					visitado[v] = true;
					resultado.add(v);
					fila.add(v);
				}
			}
			visitado[u] = true;
		}
		return resultado;
	}

	public List<Integer> profundidade(int nodo) {
		checkNodo(nodo);
		zerarVisitados(adj.length);
		List<Integer> resultado = new ArrayList<Integer>();
		profundidade(nodo, resultado);
		return resultado;
	}

	private void profundidade(int nodo, List<Integer> resultado) {
		resultado.add(nodo);
		visitado[nodo] = true;
		for (Integer adjacente : getAdjacentes(nodo)) {
			if (!visitado[adjacente]) {
				profundidade(adjacente, resultado);
			}
		}
	}

	private List<Integer> getAdjacentes(int nodo) {
		List<Integer> adjacentes = new ArrayList<>();
		for (int j = 1; j < adj.length; j++) {
			if (adj[nodo][j] != 0) {
				adjacentes.add(j);
			}
		}
		return adjacentes;
	}

	private List<Integer> getIncidentes(int nodo) {
		List<Integer> incidentes = new ArrayList<>();
		for (int i = 1; i < adj.length; i++) {
			if (adj[i][nodo] != 0) {
				incidentes.add(i);
			}
		}
		return incidentes;
	}

	public int getGrauEntrada(int nodo) {
		checkNodo(nodo);
		int entradas = getIncidentes(nodo).size();
		return entradas;
	}

	private void checkNodo(int nodo) {
		if (nodo < 1 || nodo > adj.length) {
			throw new IllegalArgumentException("Nodo inválido!");
		}
	}

	public int getGrauSaida(int nodo) {
		checkNodo(nodo);
		int saidas = getAdjacentes(nodo).size();
		return saidas;
	}

	public int getGrau(int nodo) {
		checkNodo(nodo);
		int grau = getGrauEntrada(nodo) + getGrauSaida(nodo);
		return grau;
	}

	public boolean isFonte(int nodo) {
		checkNodo(nodo);
		return getGrauEntrada(nodo) == 0 && getGrauSaida(nodo) != 0;
	}

	public boolean isSumidouro(int nodo) {
		checkNodo(nodo);
		return getGrauEntrada(nodo) != 0 && getGrauSaida(nodo) == 0;
	}
}

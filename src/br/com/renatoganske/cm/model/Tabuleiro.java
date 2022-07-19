package br.com.renatoganske.cm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Tabuleiro {

	private int qntLinhas;
	private int qntColunas;
	private int qntMinas;

	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int qntLinhas, int qntColunas, int qntMinas) {
		this.qntLinhas = qntLinhas;
		this.qntColunas = qntColunas;
		this.qntMinas = qntMinas;

		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}
	
	public void abrir(int linha, int coluna) {
		campos.parallelStream()
			.filter( c -> c.getLinha() == linha && c.getColuna() == coluna)
			.findFirst()
			.ifPresent(c -> c.abrir());
	}
	
	public void alternarMarcacao(int linha, int coluna) {
		campos.parallelStream()
			.filter( c -> c.getLinha() == linha && c.getColuna() == coluna)
			.findFirst()
			.ifPresent(c -> c.alternarMarcacao());
	}

	private void gerarCampos() {
		for (int l = 0; l < qntLinhas; l++) {
			for (int c = 0; c < qntColunas; c++) {
				campos.add(new Campo(l, c));
			}
		}
	}

	private void associarVizinhos() {
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}

	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();

		do {
			minasArmadas = campos.stream().filter(minado).count();
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
		} while (minasArmadas < qntMinas);
	}

	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}

	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		int i = 0;
		for (int l = 0; l < qntLinhas; l++) {
			for (int c = 0; c < qntColunas; c++) {
				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}

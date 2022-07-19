package br.com.renatoganske.cm.model;

import java.util.ArrayList;
import java.util.List;

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

	}

}

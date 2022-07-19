package br.com.renatoganske.cm;

import br.com.renatoganske.cm.model.Tabuleiro;
import br.com.renatoganske.cm.visao.TabuleiroConsole;

public class Aplicacao {

	public static void main(String[] args) {
		
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		
		new TabuleiroConsole(tabuleiro);

	}

}

package negocio;

import java.util.Random;

import apresentacao.*;

public class Jogo {

	private Jogador jogador;
	private Tela tela;
	private Terminal terminal;
	private int numeroEscolhido;
	private boolean jogando;

	public Jogo() {
		Random random = new Random();
		tela = new Tela();
		terminal = new Terminal();
		jogador = new Jogador();
		numeroEscolhido = random.nextInt(101);
		jogando = true;
	}

	public void inciarJogoTerminal() {
		jogador.setNome(terminal.entradaNome());
		terminal.mesagem("Seja bem vindo: " + jogador.getNome());
	}

	public void inciarJogoGUI() {
		jogador.setNome(tela.entradaDados("Qual o seu nome?"));
		tela.mensagem("Seja bem vindo: " + jogador.getNome());
		jogadas();
	}

	public int solicitarNumero() {
		try {
			String numero = tela.entradaDados("Informe um numero:");
			return Integer.parseInt(numero);
		}catch(Exception e) {
			String numero = tela.entradaDados("Só são aceitos numeros inteiros, digite um numero inteiro:");
			return Integer.parseInt(numero);
		}
	}

	public void jogadas() {
		do {
			verificarAcerto();
		} while (jogando);

		fimDoJogo();
	}

	private void fimDoJogo() {

		String numeros = "";
		for (Integer numero : jogador.getListaNumeros()) {
			numeros += " - " + numero;
		}
		tela.mensagem("Numeros apostados: " + numeros);

	}

	public boolean verificarMenor(int numero) {
		if (numero < numeroEscolhido)
			return true;

		return false;
	}

	public void verificarAcerto() { // não faço ideia como diminuir isso, so sei programar do jeito feio
		int numero = solicitarNumero();
		jogador.addNumero(numero);
		if (numero == numeroEscolhido) {
			tela.mensagem("Parabens voce acertou! numero de tentativas: " + (jogador.getNumeroTentativa()+1));
			jogando = false;
		} else {
			if(jogador.getNumeroTentativa()==0) {// so entra na primeira vez
				tela.mensagem("Deu ruim,  voce errou");
			}
			jogador.setNumeroTentativa();


			if (verificarMenor(numero)) {
				tela.mensagem("Tente um numero maior");
			} else {
				tela.mensagem("Tente numero menor");
			}
		}
	}

	// public void verificarAcerto() {
	// do {
	// if (solicitarNumero() == numeroEscolhido) {
	// jogador.setNumeroTentativa();
	// tela.mesagem("Parabens voce acertou! numero de tentativas: " +
	// jogador.getNumeroTentativa());
	// return;
	// } else {
	// tela.mesagem("Deu ruim, voce errou");
	// jogador.setNumeroTentativa();
	// }
	// } while (true);
	// }
}

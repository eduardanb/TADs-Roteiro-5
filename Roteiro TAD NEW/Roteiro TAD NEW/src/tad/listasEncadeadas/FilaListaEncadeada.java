package tad.listasEncadeadas;

import tad.ElementoNaoEncontradoException;
import tad.fila.FilaCheiaException;
import tad.fila.FilaIF;
import tad.fila.FilaVaziaException;

public class FilaListaEncadeada implements FilaIF<NodoListaEncadeada<Integer>> {

	private ListaEncadeadaImpl<Integer> fila;
	private int limite;

	public FilaListaEncadeada(int limite) {
		this.fila = new ListaEncadeadaImpl<>();
		this.limite = limite;
	}

	public FilaListaEncadeada() {
		this.fila = new ListaEncadeadaImpl<>();
		this.limite = Integer.MAX_VALUE;
	}

	@Override
	public void enfileirar(NodoListaEncadeada<Integer> item) throws FilaCheiaException {
		if (isFull()) {
			throw new FilaCheiaException();
		}
		fila.insert(item.getChave());
	}

	@Override
	public NodoListaEncadeada<Integer> desenfileirar() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException();
		}

		try {
			NodoListaEncadeada<Integer> primeiro = fila.cabeca.getProximo();
			fila.remove(primeiro.getChave());
			return primeiro;
		} catch (ListaVaziaException e) {
			throw new FilaVaziaException();
		} catch (ElementoNaoEncontradoException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public NodoListaEncadeada<Integer> verificarCauda() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException();
		}

		NodoListaEncadeada<Integer> atual = fila.cabeca.getProximo();
		NodoListaEncadeada<Integer> ultimo = atual;

		while (atual != null && atual != fila.getCauda()) {
			ultimo = atual;
			atual = atual.getProximo();
		}

		return ultimo;
	}

	@Override
	public NodoListaEncadeada<Integer> verificarCabeca() {
		if (isEmpty()) {
			return null;
		}
		return fila.cabeca.getProximo();
	}

	@Override
	public boolean isEmpty() {
		return fila.isEmpty();
	}

	@Override
	public boolean isFull() {
		return fila.size() >= limite;
	}

	@Override
	public int capacidade() {
		return 0;
	}

	@Override
	public int tamanho() {
		return 0;
	}
}
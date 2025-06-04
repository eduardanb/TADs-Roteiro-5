package tad.listasEncadeadas;

import java.lang.reflect.Array;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {

	NodoListaDuplamenteEncadeada<T> cabeca = null;
	NodoListaDuplamenteEncadeada<T> cauda = null;

	public ListaDuplamenteEncadeadaImpl() {
		cabeca = new NodoListaDuplamenteEncadeada<T>();
		cauda = new NodoListaDuplamenteEncadeada<T>();

		cabeca.setProximo(cauda);
		cabeca.setAnterior(cauda);

		cauda.setAnterior(cabeca);
		cauda.setProximo(cabeca);
	}

	@Override
	public boolean isEmpty() {
		return cabeca.getProximo() == null || cabeca.getProximo() == cauda;
	}

	@Override
	public int size() {
		int count = 0;
		NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
		while (atual != null && !atual.equals(cauda)) {
			count++;
			atual = atual.getProximo();
		}
		return count;
	}

	@Override
	public T[] toArray(Class<T> clazz) {
		if (isEmpty()) {
			@SuppressWarnings("unchecked")
			T[] arrayVazio = (T[]) Array.newInstance(clazz, 0);
			return arrayVazio;
		}

		int size = size();
		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(clazz, size);

		NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
		int i = 0;
		while (atual != null && !atual.equals(cauda)) {
			array[i++] = atual.getChave();
			atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
		}

		return array;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> search(T chave) {
		NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
		while (atual != null && !atual.equals(cauda)) {
			if (atual.getChave().equals(chave)) {
				return atual;
			}
			atual = atual.getProximo();
		}
		return null;
	}

	@Override
	public void insert(T chave) {
		NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<>(chave);

		novoNo.setAnterior(cauda.getAnterior());
		novoNo.setProximo(cauda);
		cauda.getAnterior().setProximo(novoNo);
		cauda.setAnterior(novoNo);
	}

	@Override
	public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException {
		if(isEmpty()) {
			throw new ListaVaziaException();
		}

		NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
		while (!atual.equals(cauda)) {
			if (atual.getChave().equals(chave)) {
				NodoListaDuplamenteEncadeada<T> anterior = (NodoListaDuplamenteEncadeada<T>) atual.getAnterior();
				NodoListaDuplamenteEncadeada<T> proximo = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();

				anterior.setProximo(proximo);
				proximo.setAnterior(anterior);

				atual.setProximo(null);
				atual.setAnterior(null);

				return atual;
			}
			atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
		}
		return null;
	}

	@Override
	public String imprimeEmOrdem() {
		StringBuilder sb = new StringBuilder();
		NodoListaDuplamenteEncadeada<T> atual = cabeca.getProximo();
		while (!atual.equals(cauda)) {
			sb.append(atual.getChave());
			if (!atual.getProximo().equals(cauda)) {
				sb.append(", ");
			}
			atual = atual.getProximo();
		}
		return sb.toString();
	}

	@Override
	public String imprimeInverso() {
		StringBuilder sb = new StringBuilder();
		NodoListaDuplamenteEncadeada<T> atual = cauda.getAnterior();

		while (atual != null && !atual.isNull() && atual != cabeca) {
			sb.append(atual.getChave());
			atual = atual.getAnterior();
			if (atual != cabeca && atual != null && !atual.isNull()) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) {
		NodoListaDuplamenteEncadeada<T> nodo = search(chave);
		if (nodo == null) {
			return null;
		}
		NodoListaDuplamenteEncadeada<T> suc = (NodoListaDuplamenteEncadeada<T>) nodo.getProximo();
		if (suc == null || suc.equals(cauda)) {
			return null;
		}
		return suc;
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) {
		NodoListaDuplamenteEncadeada<T> nodo = search(chave);
		if (nodo == null) {
			return null;
		}
		NodoListaDuplamenteEncadeada<T> pred = (NodoListaDuplamenteEncadeada<T>) nodo.getAnterior();
		if (pred.equals(cabeca)) {
			return null;
		}
		return pred;
	}

	@Override
	public void inserePrimeiro(T elemento) {
		NodoListaDuplamenteEncadeada<T> novo = new NodoListaDuplamenteEncadeada<>();
		novo.setChave(elemento);
		novo.setProximo(cabeca.getProximo());
		novo.setAnterior(cabeca);

		cabeca.getProximo().setAnterior(novo);
		cabeca.setProximo(novo);
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removeUltimo() throws ListaVaziaException {
		if (isEmpty()) {
			throw new ListaVaziaException();
		}

		NodoListaDuplamenteEncadeada<T> ultimo = cauda.getAnterior();
		NodoListaDuplamenteEncadeada<T> penultimo = (NodoListaDuplamenteEncadeada<T>) ultimo.getAnterior();

		penultimo.setProximo(cauda);
		cauda.setAnterior(penultimo);

		ultimo.setProximo(null);
		ultimo.setAnterior(null);

		return ultimo;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removePrimeiro() throws ListaVaziaException {
		if (isEmpty()) {
			throw new ListaVaziaException();
		}

		NodoListaDuplamenteEncadeada<T> removido = (NodoListaDuplamenteEncadeada<T>) cabeca.getProximo();
		NodoListaDuplamenteEncadeada<T> novoPrimeiro = (NodoListaDuplamenteEncadeada<T>) removido.getProximo();

		cabeca.setProximo(novoPrimeiro);
		novoPrimeiro.setAnterior(cabeca);

		removido.setProximo(null);
		removido.setAnterior(null);

		return removido;
	}

	@Override
	public void insert(T chave, int index) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index fora do intervalo");
		}

		NodoListaDuplamenteEncadeada<T> novo = new NodoListaDuplamenteEncadeada<>(chave);

		NodoListaDuplamenteEncadeada<T> atual = cabeca;
		for (int i = 0; i < index; i++) {
			atual = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();
		}

		NodoListaDuplamenteEncadeada<T> proximo = (NodoListaDuplamenteEncadeada<T>) atual.getProximo();

		novo.setProximo(proximo);
		novo.setAnterior(atual);

		atual.setProximo(novo);
		proximo.setAnterior(novo);
	}

	public NodoListaEncadeada<T> getCauda(){
		return this.cauda;
	}

	public NodoListaDuplamenteEncadeada<T> getCabeca() {
		return cabeca;
	}
}
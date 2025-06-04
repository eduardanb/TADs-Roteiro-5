package tad.listasEncadeadas;

import tad.ElementoNaoEncontradoException;

public class ListaEncadeadaImpl<T extends Comparable<T>> implements ListaEncadeadaIF<T> {

	public NodoListaEncadeada<T> cabeca = null;
	NodoListaEncadeada<T> cauda = null;
	private int tamanho = 0;

	public ListaEncadeadaImpl() {
		cabeca = new NodoListaEncadeada<T>();
		cauda = new NodoListaEncadeada<T>();
		cabeca.setProximo(cauda);
	}

	@Override
	public boolean isEmpty() {
		return tamanho == 0;
	}

	@Override
	public int size() {
		int count = 0;
		NodoListaEncadeada<T> atual = cabeca.getProximo();
		while (!atual.equals(cauda)) {
			count++;
			atual = atual.getProximo();
		}
		return count;
	}

	@Override
	public NodoListaEncadeada<T> search(T chave) {
		if (chave == null) return null;
		NodoListaEncadeada<T> atual = cabeca.getProximo();
		while (atual != cauda) {
			if (chave.equals(atual.getChave())) {
				return atual;
			}
			atual = atual.getProximo();
		}
		return null;
	}

	@Override
	public void insert(T chave) {
		NodoListaEncadeada<T> novo = new NodoListaEncadeada<>(chave);
		NodoListaEncadeada<T> atual = cabeca;
		while (atual.getProximo() != cauda) {
			atual = atual.getProximo();
		}
		novo.setProximo(cauda);
		atual.setProximo(novo);
		tamanho++;
	}

	@Override
	public NodoListaEncadeada<T> remove(T chave) throws ListaVaziaException, ElementoNaoEncontradoException {
		if (isEmpty()) {
			throw new ListaVaziaException();
		}
		NodoListaEncadeada<T> anterior = cabeca;
		NodoListaEncadeada<T> atual = cabeca.getProximo();
		while (!atual.equals(cauda) && atual.getChave().compareTo(chave) != 0) {
			anterior = atual;
			atual = atual.getProximo();
		}
		if (atual.equals(cauda)) {
			throw new ElementoNaoEncontradoException("Elemento " + chave + " não encontrado.");
		}
		anterior.setProximo(atual.getProximo());
		atual.setProximo(null);
		tamanho--;
		return atual;
	}

	@Override
	public T[] toArray(Class<T> clazz) {
		int tamanho = size();
		@SuppressWarnings("unchecked")
		T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, tamanho);
		if (tamanho == 0) {
			return array;
		}
		NodoListaEncadeada<T> atual = cabeca.getProximo();
		int i = 0;
		while (!atual.equals(cauda) && i < tamanho) {
			array[i++] = atual.getChave();
			atual = atual.getProximo();
		}
		return array;
	}

	@Override
	public String imprimeEmOrdem() {
		if(isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		NodoListaEncadeada<T> atual = cabeca.getProximo();
		while(atual != null && atual != cauda) {
			sb.append(atual.getChave());
			if(atual.getProximo() != null && atual.getProximo() != cauda) {
				sb.append(", ");
			}
			atual = atual.getProximo();
		}
		return sb.toString();
	}

	@Override
	public String imprimeInverso() {
		if(isEmpty()) {
			return "";
		}
		return imprimeInversoRec(cabeca.getProximo()).toString();
	}

	private StringBuilder imprimeInversoRec(NodoListaEncadeada<T> nodo) {
		if(nodo == null || nodo == cauda) {
			return new StringBuilder();
		}
		StringBuilder sb = imprimeInversoRec(nodo.getProximo());
		if(sb.length() > 0) {
			sb.append(", ");
		}
		sb.append(nodo.getChave());
		return sb;
	}

	@Override
	public NodoListaEncadeada<T> sucessor(T chave) throws ElementoNaoEncontradoException {
		NodoListaEncadeada<T> nodo = search(chave);
		if (nodo == null) {
			throw new ElementoNaoEncontradoException("Elemento " + chave + " não encontrado na lista.");
		}
		return nodo.getProximo();
	}

	@Override
	public NodoListaEncadeada<T> predecessor(T chave) throws ElementoNaoEncontradoException {
		if (isEmpty()) {
			throw new ElementoNaoEncontradoException("Lista vazia.");
		}
		NodoListaEncadeada<T> anterior = cabeca;
		NodoListaEncadeada<T> atual = cabeca.getProximo();
		while (atual != null) {
			T chaveAtual = atual.getChave();
			if (chaveAtual != null && chaveAtual.compareTo(chave) == 0) {
				if (anterior.equals(cabeca)) {
					throw new ElementoNaoEncontradoException("Não há predecessor para o primeiro elemento.");
				} else {
					return anterior;
				}
			}
			anterior = atual;
			atual = atual.getProximo();
		}
		throw new ElementoNaoEncontradoException("Elemento não encontrado na lista.");
	}

	@Override
	public void insert(T chave, int index) {
		if(index < 0 || index > tamanho) {
			throw new IndexOutOfBoundsException("Índice fora do intervalo");
		}
		NodoListaEncadeada<T> novoNo = new NodoListaEncadeada<>(chave);
		NodoListaEncadeada<T> atual = cabeca;
		for(int i = 0; i < index; i++) {
			atual = atual.getProximo();
		}
		novoNo.setProximo(atual.getProximo());
		atual.setProximo(novoNo);
		tamanho++;
	}

	public NodoListaEncadeada<T> getCauda() {
		return this.cauda;
	}
}

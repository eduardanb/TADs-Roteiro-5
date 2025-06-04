package tad.listasEncadeadas;

import java.lang.reflect.Array;

/**
 * Implementação de uma lista duplamente encadeada com nós sentinela (cabeça e cauda).
 * A lista mantém referências para o próximo e anterior nó, permitindo travessia bidirecional.
 *
 * @param <T> Tipo dos elementos armazenados na lista, deve implementar Comparable
 */
public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {

	// Nó sentinela que representa o início da lista
	NodoListaDuplamenteEncadeada<T> cabeca = null;
	// Nó sentinela que representa o fim da lista
	NodoListaDuplamenteEncadeada<T> cauda = null;

	/**
	 * Construtor que inicializa a lista vazia com nós sentinela.
	 * A cabeça e cauda são configuradas para apontar uma para a outra.
	 */
	public ListaDuplamenteEncadeadaImpl() {
		cabeca = new NodoListaDuplamenteEncadeada<T>();
		cauda = new NodoListaDuplamenteEncadeada<T>();

		cabeca.setProximo(cauda);
		cabeca.setAnterior(cauda);

		cauda.setAnterior(cabeca);
		cauda.setProximo(cabeca);
	}

	/**
	 * Verifica se a lista está vazia.
	 * @return true se a lista estiver vazia, false caso contrário
	 */
	@Override
	public boolean isEmpty() {
		return cabeca.getProximo() == null || cabeca.getProximo() == cauda;
	}

	/**
	 * Retorna o número de elementos na lista.
	 * @return Quantidade de elementos na lista
	 */
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

	/**
	 * Converte a lista em um array do tipo especificado.
	 * @param clazz Classe do tipo T para criação do array
	 * @return Array contendo todos os elementos da lista
	 */
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

	/**
	 * Busca um elemento na lista.
	 * @param chave Elemento a ser buscado
	 * @return Nó contendo o elemento ou null se não encontrado
	 */
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

	/**
	 * Insere um novo elemento no final da lista.
	 * @param chave Elemento a ser inserido
	 */
	@Override
	public void insert(T chave) {
		NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<>(chave);

		novoNo.setAnterior(cauda.getAnterior());
		novoNo.setProximo(cauda);
		cauda.getAnterior().setProximo(novoNo);
		cauda.setAnterior(novoNo);
	}

	/**
	 * Remove um elemento específico da lista.
	 *
	 * @param chave Elemento a ser removido
	 * @return Nó removido
     */
	@Override
	public NodoListaDuplamenteEncadeada<T> remove(T chave) throws ListaVaziaException {
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

	/**
	 * Retorna uma string com os elementos em ordem.
	 * @return String formatada com elementos separados por vírgula
	 */
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

	/**
	 * Retorna uma string com os elementos em ordem inversa.
	 * @return String formatada com elementos separados por vírgula (ordem inversa)
	 */
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

	/**
	 * Retorna o sucessor de um elemento na lista.
	 * @param chave Elemento para encontrar o sucessor
	 * @return Nó sucessor ou null se não existir
	 */
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

	/**
	 * Retorna o predecessor de um elemento na lista.
	 * @param chave Elemento para encontrar o predecessor
	 * @return Nó predecessor ou null se não existir
	 */
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

	/**
	 * Insere um elemento no início da lista.
	 * @param elemento Elemento a ser inserido
	 */
	@Override
	public void inserePrimeiro(T elemento) {
		NodoListaDuplamenteEncadeada<T> novo = new NodoListaDuplamenteEncadeada<>();
		novo.setChave(elemento);
		novo.setProximo(cabeca.getProximo());
		novo.setAnterior(cabeca);

		cabeca.getProximo().setAnterior(novo);
		cabeca.setProximo(novo);
	}

	/**
	 * Remove o último elemento da lista.
	 * @return Nó removido
     */
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

	/**
	 * Remove o primeiro elemento da lista.
	 * @return Nó removido
     */
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

	/**
	 * Insere um elemento em uma posição específica na lista.
	 * @param chave Elemento a ser inserido
	 * @param index Posição de inserção (0-based)
	 * @throws IndexOutOfBoundsException Se o índice for inválido
	 */
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

	// Métodos de acesso para testes

	/**
	 * Retorna o nó sentinela cauda (para fins de teste)
	 * @return Nó cauda
	 */
	public NodoListaEncadeada<T> getCauda(){
		return this.cauda;
	}

	/**
	 * Retorna o nó sentinela cabeça (para fins de teste)
	 * @return Nó cabeça
	 */
	public NodoListaDuplamenteEncadeada<T> getCabeca() {
		return cabeca;
	}
}
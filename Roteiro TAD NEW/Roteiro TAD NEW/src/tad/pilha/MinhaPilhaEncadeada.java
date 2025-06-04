package tad.pilha;

import tad.listasEncadeadas.ListaEncadeadaImpl;
import tad.listasEncadeadas.ListaVaziaException;
import tad.ElementoNaoEncontradoException;

/**
 * Implementação de uma pilha encadeada de inteiros com operações básicas e tratamento de exceções.
 */
public class MinhaPilhaEncadeada implements PilhaIF<Integer> {

	private final ListaEncadeadaImpl<Integer> listaEncadeada = new ListaEncadeadaImpl<>();

	/**
	 * Método para inserir um elemento no topo da pilha.
	 * @param item o elemento a ser empilhado.
	 * @throws PilhaCheiaException nunca é lançado, pois a pilha encadeada não tem limite.
	 */
	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		listaEncadeada.insert(item);
	}

	/**
	 * Método para remover e retornar o elemento do topo da pilha.
	 * @return o elemento do topo.
	 * @throws PilhaVaziaException se a pilha estiver vazia.
	 */
	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (listaEncadeada.isEmpty()) {
			throw new PilhaVaziaException();
		}

		int index = listaEncadeada.size() - 1;
		try {
			Integer topo = listaEncadeada.toArray(Integer.class)[index];
			listaEncadeada.remove(topo);
			return topo;
		} catch (ElementoNaoEncontradoException | ListaVaziaException e) {
			throw new PilhaVaziaException();
		}
	}

	/**
	 * Método para retornar o elemento do topo da pilha sem removê-lo.
	 * @return o elemento do topo ou null se a pilha estiver vazia.
	 */
	@Override
	public Integer topo() {
		if (listaEncadeada.isEmpty()) {
			return null;
		}
		Integer[] array = listaEncadeada.toArray(Integer.class);
		return array[array.length - 1];
	}

	/**
	 * Método que retorna uma nova pilha contendo os k elementos do topo da pilha atual.
	 * A ordem dos elementos na nova pilha preserva a ordem de desempilhamento.
	 * @param k número de elementos do topo a copiar.
	 * @return nova pilha com os k elementos do topo.
	 * @throws IllegalArgumentException se k for menor ou igual a zero.
	 */
	@Override
	public PilhaIF<Integer> multitop(int k) throws IllegalArgumentException {
		if (k <= 0) {
			throw new IllegalArgumentException("k deve ser positivo");
		}

		MinhaPilhaEncadeada novaPilha = new MinhaPilhaEncadeada();
		Integer[] array = listaEncadeada.toArray(Integer.class);
		int total = array.length;

		for (int i = total - k; i < total; i++) {
			if (i >= 0) {
				novaPilha.listaEncadeada.insert(array[i]);
			}
		}

		return novaPilha;
	}

	/**
	 * Método que verifica se a pilha está vazia.
	 * @return true se a pilha estiver vazia, false caso contrário.
	 */
	@Override
	public boolean isEmpty() {
		return listaEncadeada.isEmpty();
	}

	/**
	 * Método para retornar a capacidade da pilha.
	 * @return sempre 0, pois indica que não tem limite de capacidade.
	 */
	@Override
	public int capacidade() {
		return 0;
	}

	/**
	 * Método para retornar o tamanho atual da pilha.
	 * @return o tamanho atual da pilha.
	 */
	@Override
	public int tamanho() {
		return listaEncadeada.size();
	}
}

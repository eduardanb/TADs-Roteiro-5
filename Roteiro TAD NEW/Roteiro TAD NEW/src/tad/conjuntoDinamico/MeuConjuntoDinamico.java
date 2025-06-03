package tad.conjuntoDinamico;

import tad.ElementoNaoEncontradoException;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

	private Integer[] meusDados;
	private int posInsercao;

	public MeuConjuntoDinamico() {
		meusDados = new Integer[10]; // capacidade inicial
		posInsercao = 0;
	}

	@Override
	public void inserir(Integer item) {
		if (item == null) throw new IllegalArgumentException("Elemento não pode ser null");

		if (posInsercao == meusDados.length) {
			meusDados = aumentarArray();
		}

		meusDados[posInsercao++] = item;
	}

	private Integer[] aumentarArray() {
		int novaCapacidade = meusDados.length + meusDados.length / 2 + 1;
		Integer[] novoArray = new Integer[novaCapacidade];

        System.arraycopy(meusDados, 0, novoArray, 0, meusDados.length);

		return novoArray;
	}

	@Override
	public Integer remover(Integer item) throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException {
		if (item == null) throw new IllegalArgumentException("Elemento não pode ser null");

		if (posInsercao == 0) throw new ConjuntoDinamicoVazioException("Conjunto dinâmico Vazio!!!");

		for (int i = 0; i < posInsercao; i++) {
			if (meusDados[i].equals(item)) {
				Integer removido = meusDados[i];

				// move todos os elementos à direita para a esquerda
				for (int j = i; j < posInsercao - 1; j++) {
					meusDados[j] = meusDados[j + 1];
				}

				meusDados[--posInsercao] = null;
				return removido;
			}
		}

		throw new ElementoNaoEncontradoException("Elemento nao encontrado!!");
	}

	@Override
	public Integer predecessor(Integer item) throws ConjuntoDinamicoVazioException {
		if (posInsercao == 0) throw new ConjuntoDinamicoVazioException("Conjunto dinâmico Vazio!!!");

		Integer pred = null;
		for (int i = 0; i < posInsercao; i++) {
			if (meusDados[i] < item) {
				if (pred == null || meusDados[i] > pred) {
					pred = meusDados[i];
				}
			}
		}
		return pred;
	}

	@Override
	public Integer sucessor(Integer item) throws ConjuntoDinamicoVazioException {
		if (posInsercao == 0) throw new ConjuntoDinamicoVazioException("Conjunto dinâmico Vazio!!!");

		Integer suc = null;
		for (int i = 0; i < posInsercao; i++) {
			if (meusDados[i] > item) {
				if (suc == null || meusDados[i] < suc) {
					suc = meusDados[i];
				}
			}
		}
		return suc;
	}

	@Override
	public int tamanho() {
		return posInsercao;
	}

	@Override
	public Integer buscar(Integer item) throws ElementoNaoEncontradoException {
		if (item == null) throw new IllegalArgumentException("Elemento não pode ser null");

		for (int i = 0; i < posInsercao; i++) {
			if (meusDados[i].equals(item)) {
				return meusDados[i];
			}
		}

		throw new ElementoNaoEncontradoException("Elemento nao encontrado!!");
	}

	@Override
	public Integer minimum() throws ConjuntoDinamicoVazioException {
		if (posInsercao == 0) throw new ConjuntoDinamicoVazioException("Conjunto dinâmico Vazio!!!");

		Integer min = meusDados[0];
		for (int i = 1; i < posInsercao; i++) {
			if (meusDados[i] < min) {
				min = meusDados[i];
			}
		}
		return min;
	}

	@Override
	public Integer maximum() throws ConjuntoDinamicoVazioException {
		if (posInsercao == 0) throw new ConjuntoDinamicoVazioException("Conjunto dinâmico Vazio!!!");

		Integer max = meusDados[0];
		for (int i = 1; i < posInsercao; i++) {
			if (meusDados[i] > max) {
				max = meusDados[i];
			}
		}
		return max;
	}
}

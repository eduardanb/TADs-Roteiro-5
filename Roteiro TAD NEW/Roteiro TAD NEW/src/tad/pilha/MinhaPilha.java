package tad.pilha;

/**
 * Implementação de uma pilha genérica usando array.
 * A pilha armazena elementos do tipo Integer e possui operações básicas de pilha.
 */

public class MinhaPilha implements PilhaIF<Integer> {

	private int tamanho = 5;
	private Integer[] meusDados = null;
	private int indiceTopo = -1;

	public MinhaPilha(int tamanho) {
		this.tamanho = tamanho;
		this.meusDados = new Integer[tamanho];
	}

	public MinhaPilha() {
		this.meusDados = new Integer[tamanho];
	}

	/**
	 * Método para inserir um elemento no topo da pilha, lançando exceção se a pilha estiver cheia.
	 */
	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if (indiceTopo == meusDados.length - 1) {
			throw new PilhaCheiaException();
		} else {
			meusDados[++indiceTopo] = item;
		}
	}

	/**
	 * Método para remover e retornar o elemento do topo da pilha.
	 */
	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (!isEmpty()) {
			return meusDados[indiceTopo--];
		} else {
			throw new PilhaVaziaException("pilha vazia!!");
		}
	}

	/**
	 * Método para retornar o elemento do topo da pilha sem removê-lo.
	 */
	@Override
	public Integer topo() {
		if (!isEmpty()) {
			return meusDados[indiceTopo];
		} else {
			return null;
		}
	}

	/**
	 * Método que retorna uma nova pilha contendo os k elementos do topo da pilha atual.
	 * A ordem dos elementos na nova pilha preserva a ordem de desempilhamento.
	 */
	@Override
	public PilhaIF<Integer> multitop(int k) {
		if (k <= 0) {
			throw new IllegalArgumentException("k deve ser positivo");
		}

		MinhaPilha novaPilha = new MinhaPilha(Math.min(k, this.capacidade()));
		int limite = Math.min(k, indiceTopo + 1);

		for (int i = indiceTopo; i >= indiceTopo - limite + 1; i--) {
			try {
				novaPilha.empilhar(meusDados[i]);
			} catch (PilhaCheiaException e) {
				e.printStackTrace();
			}
		}
		return novaPilha;
	}

	/**
	 * Método que verifica se a pilha está vazia.
	 */
	@Override
	public boolean isEmpty() {
		return this.indiceTopo == -1;
	}

	/**
	 * Método que compara esta pilha com outro objeto para verificar igualdade.
	 * Duas pilhas são iguais se tiverem o mesmo número de elementos e todos os elementos, na mesma ordem, forem iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		MinhaPilha outra = (MinhaPilha) obj;

		if (this.indiceTopo != outra.indiceTopo) return false;

		for (int i = 0; i <= indiceTopo; i++) {
			if (!this.meusDados[i].equals(outra.meusDados[i])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Método que gera o código hash da pilha com base nos elementos armazenados.
	 */
	@Override
	public int hashCode() {
		int result = 1;
		for (int i = 0; i <= indiceTopo; i++) {
			result = 31 * result + meusDados[i].hashCode();
		}
		return result;
	}

	/**
	 * Método que retorna a capacidade máxima da pilha.
	 */
	@Override
	public int capacidade() {
		return meusDados.length;
	}

	/**
	 * Método que retorna o tamanho atual da pilha (número de elementos).
	 */
	@Override
	public int tamanho() {
		return indiceTopo + 1;
	}
}

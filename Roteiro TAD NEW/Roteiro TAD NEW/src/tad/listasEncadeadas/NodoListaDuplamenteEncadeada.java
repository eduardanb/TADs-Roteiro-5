package tad.listasEncadeadas;

public class NodoListaDuplamenteEncadeada<T extends Comparable<T>> extends NodoListaEncadeada<T> {
	protected NodoListaDuplamenteEncadeada<T> anterior;

	public NodoListaDuplamenteEncadeada() {
		super();
		this.anterior = null;
	}

	public NodoListaDuplamenteEncadeada(T chave) {
		super(chave);
		this.anterior = null;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> getProximo() {
		return (NodoListaDuplamenteEncadeada<T>) proximo;
	}

	public NodoListaDuplamenteEncadeada<T> getAnterior() {
		return anterior;
	}

	public void setAnterior(NodoListaDuplamenteEncadeada<T> anterior) {
		this.anterior = anterior;
	}

	public void setProximo(NodoListaDuplamenteEncadeada<T> proximo) {
		this.proximo = proximo;
	}
}
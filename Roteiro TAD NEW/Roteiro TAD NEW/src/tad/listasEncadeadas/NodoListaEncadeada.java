package tad.listasEncadeadas;

import java.util.Objects;

public class NodoListaEncadeada<T extends Comparable<T>> {

	protected T chave;
	protected NodoListaEncadeada<T> proximo = null;

	public NodoListaEncadeada() {
		this.setChave(null);
		this.setProximo(null);
	}

	public NodoListaEncadeada(T chave) {
		this.setChave(chave);
		this.setProximo(null);
	}

	public NodoListaEncadeada(T chave, NodoListaEncadeada<T> proximo) {
		this.setChave(chave);
		this.setProximo(proximo);
	}

	public T getChave() {
		return chave;
	}

	public void setChave(T chave) {
		this.chave = chave;
	}

	public NodoListaEncadeada<T> getProximo() {
		return proximo;
	}

	public void setProximo(NodoListaEncadeada<T> proximo) {
		this.proximo = proximo;
	}

	public boolean isNull() {
		return (chave == null ? true:false);
	}

	@Override
	public String toString() {
		if (!this.isNull())
			return this.chave.toString();
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		NodoListaEncadeada<?> that = (NodoListaEncadeada<?>) obj;
		return Objects.equals(chave, that.chave);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chave);
	}


}
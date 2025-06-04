package tad.listasEncadeadas;

public interface ListaDuplamenteEncadeadaIF<T extends Comparable<T>> extends ListaEncadeadaIF<T>{

	public void inserePrimeiro(T elemento);
	NodoListaDuplamenteEncadeada<T> removeUltimo() throws ListaVaziaException;
	public NodoListaDuplamenteEncadeada<T> removePrimeiro() throws ListaVaziaException;

}
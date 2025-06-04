package tad.fila;

import tad.ElementoNaoEncontradoException;
import tad.listasEncadeadas.ListaEncadeadaImpl;
import tad.listasEncadeadas.ListaVaziaException;

public class MinhaFilaEncadeada implements FilaIF<Integer> {

	private int tamanho = 5;
	private ListaEncadeadaImpl<Integer> listaEncadeada = null;

	public MinhaFilaEncadeada(int tamanho){
		this.tamanho = tamanho;
		this.listaEncadeada = new ListaEncadeadaImpl<>();
	}

	public MinhaFilaEncadeada(){
		listaEncadeada = new ListaEncadeadaImpl<>();
	}

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		if(this.listaEncadeada.size() == this.tamanho){
			throw new FilaCheiaException();
		}
		this.listaEncadeada.insert(item);
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		Integer numeroRemovido = null;
		try{
			numeroRemovido = this.listaEncadeada.remove(this.listaEncadeada.cabeca.getChave()).getChave();
		} catch(ElementoNaoEncontradoException | ListaVaziaException e){
			System.out.println(e.getMessage());
		}
		return numeroRemovido;
	}

	@Override
	public Integer verificarCauda() {
		if(this.listaEncadeada.isEmpty()){
			return null;
		}
		return this.listaEncadeada.getCauda().getChave();
	}

	@Override
	public Integer verificarCabeca() {
		if(this.listaEncadeada.isEmpty()){
			return null;
		}
		return this.listaEncadeada.cabeca.getChave();
	}

	@Override
	public boolean isEmpty() {
		return this.listaEncadeada.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.listaEncadeada.size() == this.tamanho;
	}

	@Override
	public int capacidade() {
		return this.tamanho;
	}

	@Override
	public int tamanho() {
		return this.listaEncadeada.size();
	}
}
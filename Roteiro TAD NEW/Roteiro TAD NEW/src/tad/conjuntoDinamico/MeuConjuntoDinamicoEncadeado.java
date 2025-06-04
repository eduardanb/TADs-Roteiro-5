package tad.conjuntoDinamico;

import tad.ElementoNaoEncontradoException;
import tad.listasEncadeadas.ListaEncadeadaImpl;
import tad.listasEncadeadas.ListaVaziaException;

public class MeuConjuntoDinamicoEncadeado implements ConjuntoDinamicoIF<Integer> {
	private ListaEncadeadaImpl<Integer> conjuntoEncadeado = null;

	public MeuConjuntoDinamicoEncadeado(){
		this.conjuntoEncadeado = new ListaEncadeadaImpl<>();
	}

	@Override
	public void inserir(Integer item) {
		this.conjuntoEncadeado.insert(item);
	}

	@Override
	public Integer remover(Integer item) {
		try{
			return this.conjuntoEncadeado.remove(item).getChave();
		}catch(ListaVaziaException  | ElementoNaoEncontradoException e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Integer predecessor(Integer item) {
		try{
			return this.conjuntoEncadeado.predecessor(item).getChave();
		}catch(ElementoNaoEncontradoException e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public Integer sucessor(Integer item){
		try{
			return this.conjuntoEncadeado.sucessor(item).getChave();
		} catch(ElementoNaoEncontradoException e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public int tamanho() {
		return this.conjuntoEncadeado.size();
	}

	@Override
	public Integer buscar(Integer item) {
		if(this.conjuntoEncadeado.isEmpty()){
			return null;
		}
		return this.conjuntoEncadeado.search(item).getChave();
	}

	@Override
	public Integer minimum() {
		Integer[] arrayDosElementos = this.conjuntoEncadeado.toArray(Integer.class);
		Integer menorNumero = arrayDosElementos[0];

		for(int i = 0; i < arrayDosElementos.length; i++){
			if(arrayDosElementos[i] <= menorNumero){
				menorNumero = arrayDosElementos[i];
			}
		}

		return menorNumero;
	}

	@Override
	public Integer maximum() {
		Integer[] arrayDosElementos = this.conjuntoEncadeado.toArray(Integer.class);
		Integer maiorNumero = arrayDosElementos[0];

		for(int i = 0; i < arrayDosElementos.length; i++){
			if(arrayDosElementos[i] >= maiorNumero){
				maiorNumero = arrayDosElementos[i];
			}
		}

		return maiorNumero;
	}
}
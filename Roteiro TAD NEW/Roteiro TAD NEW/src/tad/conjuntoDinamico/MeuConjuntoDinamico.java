package tad.conjuntoDinamico;

import tad.ElementoNaoEncontradoException;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer>{

	private int tamanho = 10;
	private Integer[] meusDados = null;
	private int posInsercao = 0;

	public MeuConjuntoDinamico(){
		this.meusDados = new Integer[tamanho];
	}

	@Override
	public void inserir(Integer item) throws IllegalArgumentException{
		if(item == null){
			throw new IllegalArgumentException("Elemento não pode ser null");
		}
		if(this.posInsercao == tamanho - 1){
			this.meusDados = aumentarArray();
		}
		this.meusDados[posInsercao] = item;
		posInsercao++;
	}

	private Integer[] aumentarArray() {
		this.tamanho += 10;
		Integer[] novoArray = new Integer[this.tamanho];
		int i = 0;
		for(Integer numero : this.meusDados){
			novoArray[i] = numero;
			i++;
		}
		return novoArray;
	}

	@Override
	public Integer remover(Integer item) throws ConjuntoDinamicoVazioException, ElementoNaoEncontradoException {
		if(item == null){
			throw new IllegalArgumentException("Elemento não pode ser null");
		}
		if(this.posInsercao == 0){
			throw new ConjuntoDinamicoVazioException();
		}

		boolean numeroEncontrado = false;
		Integer numeroRemovido = null;
		for(int i = 0; i < posInsercao; i++){
			if(this.meusDados[i].equals(item)){
				numeroEncontrado = true;
				numeroRemovido = this.meusDados[i];
				this.meusDados[i] = this.meusDados[i + 1];
				this.meusDados[i + 1] = numeroRemovido;
			}
		}

		if(!numeroEncontrado){
			throw new ElementoNaoEncontradoException();
		}
		else{
			this.meusDados[posInsercao] = null;
			posInsercao--;
			return numeroRemovido;
		}
	}

	@Override
	public Integer predecessor(Integer item) throws ConjuntoDinamicoVazioException{
		if(this.posInsercao == 0){
			throw new ConjuntoDinamicoVazioException();
		}
		boolean elementoEncontrado = false;
		Integer indicePredecessor = 0;
		for(int i = 0; i < posInsercao; i++){
			if(this.meusDados[i].equals(item)){
				elementoEncontrado = true;
				indicePredecessor = i - 1;
			}
		}

		if(!elementoEncontrado){
			return null;
		}

		if(indicePredecessor  < 0){
			return null;
		}

		return this.meusDados[indicePredecessor];
	}

	@Override
	public Integer sucessor(Integer item) throws ConjuntoDinamicoVazioException{
		if(this.posInsercao == 0) {
			throw new ConjuntoDinamicoVazioException();
		}
		boolean elementoEncontrado = false;
		Integer indiceSucessor = 0;

		for(int i = 0; i < posInsercao; i++){
			if(this.meusDados[i].equals(item)){
				elementoEncontrado = true;
				indiceSucessor = i + 1;
			}
		}

		if(!elementoEncontrado){
			return null;
		}

		if(indiceSucessor == posInsercao){
			return null;
		}
		return this.meusDados[indiceSucessor];
	}

	@Override
	public int tamanho() {
		return this.posInsercao;
	}

	@Override
	public Integer buscar(Integer item) throws ElementoNaoEncontradoException, IllegalArgumentException{
		if(item == null){
			throw new IllegalArgumentException("Elemento não pode ser null");
		}

		boolean numeroEncontrado = false;
		Integer numeroProcurado = null;
		for(int i = 0; i < posInsercao; i++){
			if(this.meusDados[i].equals(item)){
				numeroEncontrado = true;
			}
		}

		if(!numeroEncontrado){
			throw new ElementoNaoEncontradoException();
		}
		else{
			return item;
		}
	}

	@Override
	public Integer minimum() throws ConjuntoDinamicoVazioException{
		if(this.posInsercao == 0){
			throw new ConjuntoDinamicoVazioException();
		}

		Integer menorNumero = this.meusDados[0];
		for(int i = 0 ; i < this.posInsercao; i++){
			if(this.meusDados[i] < menorNumero){
				menorNumero = this.meusDados[i];
			}
		}
		return menorNumero;
	}

	@Override
	public Integer maximum() throws ConjuntoDinamicoVazioException{
		if(this.posInsercao == 0){
			throw new ConjuntoDinamicoVazioException();
		}

		Integer maiorNumero = this.meusDados[0];

		for(int i = 0; i < this.posInsercao ; i++){
			if(this.meusDados[i] > maiorNumero){
				maiorNumero = this.meusDados[i];
			}
		}
		return maiorNumero;
	}
}
package tad.fila;

public class MinhaFila implements FilaIF<Integer> {

	private int tamanho = 5;
	private int cauda = 1;
	private int cabeca = 0;
	private final Integer[] meusDados;

	public MinhaFila(int tamanhoInicial) {
		tamanho = tamanhoInicial;
		this.meusDados = new Integer[tamanho];
	}

	public MinhaFila() {
		this.meusDados = new Integer[tamanho];
	}

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException{
		if(this.isFull()){
			throw new FilaCheiaException();
		}
		else{
			if(this.isEmpty()){
				this.meusDados[cabeca] = item;
			}
			else{
				this.meusDados[cauda] = item;
				this.cauda++;
			}
		}
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException{
		if(this.isEmpty()){
			throw new FilaVaziaException();
		}

		Integer numeroRemovido = this.meusDados[cabeca];
		this.cauda--;
		for(int i = 0; i < this.cauda; i++){
			this.meusDados[i] = this.meusDados[i + 1];
		}

		this.meusDados[cauda] = null;
		return numeroRemovido;
	}

	@Override
	public Integer verificarCauda() {
		if(this.isEmpty()){
			return null;
		}
		return this.meusDados[cauda - 1];
	}

	@Override
	public Integer verificarCabeca() {
		if(this.isEmpty()){
			return null;
		}
		return this.meusDados[cabeca];
	}

	@Override
	public boolean isEmpty() {
		return this.tamanho() == 0;
	}

	@Override
	public boolean isFull() {
		return this.tamanho() == this.tamanho;
	}

	@Override
	public int capacidade() {
		return this.tamanho;
	}

	@Override
	public int tamanho() {
		int contadorElementos = 0;
		for(Integer numero : this.meusDados){
			if(numero != null){
				contadorElementos++;
			}
		}
		return contadorElementos;
	}
}
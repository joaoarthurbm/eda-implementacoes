package arraylist;

import util.Aluno;

/**
 * 
 * O ArrayList consiste em, basicamente, um Array que "cresce dinamicamente". 
 * 
 * O que realmente acontece eh a aplicacao de uma funcao de resize: 
 * metodo que cria um novo array e transfere os elementos do array original para o novo array.
 * 
 * A proposta do ArrayList eh fornecer uma API com operações de uma lista, mas esconder detalhes como remanejamento 
 * de elementos na remoção, aumento da capacidade da estrutura na adição de elementos, entre outras tarefas.
 * 
 * Vamos ver no codigo exemplo abaixo como eh o seu funcionamento. 
 * 
 */

public class ArrayList {

	private Aluno[] lista;
	public static final int CAPACIDADE_DEFAULT = 20; // Capacidade inicial do ArrayList eh determinada aqui.
	private int size;                                // Quantidade de elementos presentes na lista atualmente.
	
	/**
	 * Construtor com capacidade pre-determinada.
	 */
	public ArrayList() {
		this(CAPACIDADE_DEFAULT);
	}
	
	/**
	 * Construtor que recebe a capacidade desejada.
	 * 
	 * @param capacidade
	 */
	public ArrayList(int capacidade) {
		this.lista = new Aluno[capacidade];
		this.size = 0;
	}
	
	/**
	 * Metodo que checa se o ArrayList esta vazio a partir do size.
	 * 
	 * @return boolean
	 */	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	/**
	 * Metodo que retorna o valor atual do tamanho do ArrayList.
	 * 
	 * @return size
	 */
	public int size() {
		return this.size;
	}
	
	
	// METODOS DE ADICAO

	
	/**
	 * addValor:
	 * 
	 * Adiciona um novo elemento na lista recebendo um determinado valor.
	 * Nao requer um indice especifico e, por isso, assume que a insercao do novo elemento
	 * deve ser feita no fim da lista, ou seja, na proxima posicao livre do array.
	 * 
	 * @param aluno
	 * @return boolean
	 */
	public boolean add(Aluno aluno) {
		assegureCapacidade(this.size + 1);
		this.lista[size++] = aluno;
		return true;
	}
	
	/** 
	 * addIndexValor:
	 * 
	 * Adiciona um novo elemento na lista recebendo o index e o elemento.
	 * Inclui o novo elemento na posicao index e desloca os elementos a frente uma posicao
	 * para a direita (shiftParaDireita).
	 * 
	 * @param index
	 * @param aluno
	 */
	public void add(int index, Aluno aluno) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		
		assegureCapacidade(this.size + 1);
		
		shiftParaDireita(index);
		
		this.lista[index] = aluno;
		this.size += 1;
		
	}
	
	/**
	 * addSet:
	 * 
	 * Adiciona um novo elemento na lista recebendo o index e o elemento.
	 * Altera o valor da posicao index indicada.
	 * 
	 * @param index
	 * @param aluno
	 * 
	 */
	public void set(int index, Aluno aluno) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		this.lista[index] = aluno;
	}
	
	
	
	
	/**
	 * Metodo que verifica se a nova capacidade pretendida eh atendida pelo tamanho atual da lista e, 
	 * caso nao seja, invoca resize para criar uma nova lista cujo tamanho o máximo entre o dobro da lista 
	 * original ou a capacidade nova pretendida.
	 * 
	 * @param capacidadePretendida
	 */
	private void assegureCapacidade(int capacidadePretendida) {
		
		if (capacidadePretendida > this.lista.length)
			resize(Math.max(this.lista.length * 2, capacidadePretendida));
		
	}
	
	/**
	 * Metodo que cria um novo array e transfere os elementos do array original para ele.
	 * O(n).
	 * 
	 * @param novaCapacidade
	 */
	private void resize(int novaCapacidade) {
		Aluno[] novaLista = new Aluno[novaCapacidade];
		for (int i = 0; i < this.lista.length; i++)
			novaLista[i] = this.lista[i];
		this.lista = novaLista;
	}
	
	/**
	  * Esse metodo serve para deslocar os elementos a frente da posição index 
	  * para a direita, para então incluir o elemento na posição index.
	  * 
	  * @param index
	  */
	private void shiftParaDireita(int index) {
		if (index == this.lista.length - 1) 
			throw new IndexOutOfBoundsException("Não há espaço para "
					+ "efetuar o shift à direita.");
		
		for (int i = this.size; i > index; i--) {
			this.lista[i] = this.lista[i-1];
		}
	}
	
	
	// METODOS DE BUSCA
	
	/**
	 * Busca de elemento em um determinado indice.
	 * Eh executado em tempo constante O(1), pois o indice eh fornecido como parametro.
	 * 
	 * @param index
	 * @return
	 */
	public Aluno get(int index) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		return this.lista[index];
	}
	
	/**
	 * Busca do indice onde um elemento esta alocado.
	 * Eh executado com busca linear (O(n)), pois devem iterar sobre a lista procurando 
	 * pelo objeto passado como parametro.
	 * 
	 * @param aluno
	 * @return
	 */
	public int indexOf(Aluno aluno) {
		for (int i = 0; i < size; i++)
			if (this.lista[i].equals(aluno))
				return i;
		return -1;
	}
	
	/**
	 * Busca que verifica a presenca de um elemento na lista.
	 * Eh executado com busca linear (O(n)), pois devem iterar sobre a lista procurando 
	 * pelo objeto passado como parametro.
	 * 
	 * @param aluno
	 * @return 
	 */
	public boolean contains(Aluno aluno) {
		return this.indexOf(aluno) != -1;
	}
	
	
	
	// METODOS DE REMOCAO
	
	/**
	 * Remove o elemento da lista recebendo seu index.
	 * 
	 * @param index
	 * @return
	 */
	public Aluno remove(int index) {
		if (index < 0 || index >= this.size)
			return null;
		
		Aluno aluno = this.get(index);
		
		shiftParaEsquerda(index);
		this.size -= 1;
		return aluno;
	}
	
	/**
	 * Remove o elemento da lista recebendo o valor.
	 * Nesse caso, precisa procurar o elemento antes de realizar o shift.
	 * 
	 * @param aluno
	 * @return
	 */
	public boolean remove(Aluno aluno) {
		if (aluno == null) return false;
		
		for (int i = 0; i < size; i++) {
			if (this.lista[i].equals(aluno)) {
				this.remove(i);
				return true;
			}
		}
		
		return false;
	}
	

	/**
	 * Esse metodo serve para deslocar os elementos para evitar buracos na lista.
	 *  
	 * @param index
	 */
	private void shiftParaEsquerda(int index) {
		for (int i = index; i < this.size - 1; i++) {
			this.lista[i] = this.lista[i+1];
		}
	}
	
	
	public String toString() {
		if (isEmpty()) return "[]";
		
		StringBuffer st = new StringBuffer("[");
		for (int i = 0; i < this.size; i++) {
			st.append(this.lista[i].getMatricula() + ",");
		}
		
		st.replace(st.length() - 1, st.length(), "]");
		return st.toString();
	}

}

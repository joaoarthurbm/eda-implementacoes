package arraylist;

import util.Aluno;


public class ArrayList {

	private Aluno[] lista;
	public static final int CAPACIDADE_DEFAULT = 20;
	private int size;
	
	public ArrayList() {
		this(CAPACIDADE_DEFAULT);
	}
	
	public ArrayList(int capacidade) {
		this.lista = new Aluno[capacidade];
		this.size = 0;
	}
	
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean add(Aluno aluno) {
		assegureCapacidade(this.size + 1);
		this.lista[size++] = aluno;
		return true;
	}
	
	public void add(int index, Aluno aluno) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		
		assegureCapacidade(this.size + 1);
		
		shiftParaDireita(index);
		
		this.lista[index] = aluno;
		this.size += 1;
		
	}
	
	public void set(int index, Aluno aluno) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		this.lista[index] = aluno;
	}
	
	private void assegureCapacidade(int capacidadePretendida) {
		
		if (capacidadePretendida > this.lista.length)
			resize(Math.max(this.lista.length * 2, capacidadePretendida));
		
	}
	
	private void resize(int novaCapacidade) {
		Aluno[] novaLista = new Aluno[novaCapacidade];
		for (int i = 0; i < this.lista.length; i++)
			novaLista[i] = this.lista[i];
		this.lista = novaLista;
	}
	
	
	private void shiftParaDireita(int index) {
		if (index == this.lista.length - 1) 
			throw new IndexOutOfBoundsException("Não há espaço para "
					+ "efetuar o shift à direita.");
		
		for (int i = this.size; i > index; i--) {
			this.lista[i] = this.lista[i-1];
		}
	}
	
	public Aluno get(int index) {
		if (index < 0 || index >= this.size)
			throw new IndexOutOfBoundsException();
		return this.lista[index];
	}
	
	public boolean remove(int index) {
		if (index < 0 || index >= this.size)
			return false;
		
		shiftParaEsquerda(index);
		this.size -= 1;
		return true;
		
	}

	private void shiftParaEsquerda(int index) {
		for (int i = index; i < this.size - 1; i++) {
			this.lista[i] = this.lista[i+1];
		}
	}
	
	public String toString() {
		if (isEmpty()) return "[]";
		
		StringBuffer st = new StringBuffer("[");
		for (int i = 0; i < this.size; i++) {
			st.append(this.lista[i] + ",");
		}
		
		st.replace(st.length() - 1, st.length(), "]");
		return st.toString();
	}

}

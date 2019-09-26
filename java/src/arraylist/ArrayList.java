package arraylist;


public class ArrayList {

	private int[] lista;
	public static final int CAPACIDADE_DEFAULT = 20;
	private int ultimo;
	
	public ArrayList() {
		this(CAPACIDADE_DEFAULT);
	}
	
	public ArrayList(int capacidade) {
		this.lista = new int[capacidade];
		this.ultimo = -1;
	}
	
	public boolean isEmpty() {
		return this.ultimo == -1;
	}
	
	public int size() {
		return this.ultimo + 1;
	}
	
	public boolean add(int element) {
		if (this.ultimo == this.lista.length - 1)
			resize();
		this.lista[++ultimo] = element;
		return true;
	}
	
	private void resize() {
		int[] novaLista = new int[this.lista.length * 2];
		for (int i = 0; i < this.lista.length; i++)
			novaLista[i] = this.lista[i];
		this.lista = novaLista;
	}
	
	public int get(int index) {
		if (index >= 0 && index <= this.ultimo)
			return this.lista[index];
		throw new IndexOutOfBoundsException();
	}
	
	public boolean remove(int index) {
		if (index >= 0 && index <= this.ultimo) {
			shiftParaEsquerda(index);
			this.ultimo--;
			return true;
		}
		return false;
	}

	private void shiftParaEsquerda(int index) {
		for (int i = index; i < this.ultimo; i++) {
			this.lista[i] = this.lista[i+1];
		}
	}
	
	public String toString() {
		if (isEmpty()) return "[]";
		
		StringBuffer st = new StringBuffer("[");
		for (int i = 0; i <= this.ultimo; i++) {
			st.append(this.lista[i] + ",");
		}
		
		st.replace(st.length() - 1, st.length(), "]");
		return st.toString();
	}
	
	
}

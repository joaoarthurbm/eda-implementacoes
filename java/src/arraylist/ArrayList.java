package arraylist;

import util.Aluno;


public class ArrayList {

    private Aluno[] lista;
    public static final int CAPACIDADE_DEFAULT = 20;
    private int tamanho;
    
    public ArrayList() {
        this(CAPACIDADE_DEFAULT);
    }
    
    public ArrayList(int capacidade) {
        this.lista = new Aluno[capacidade];
        this.tamanho = 0;
    }
    
    public boolean isEmpty() {
        return this.tamanho == 0;
    }
    
    public int size() {
        return this.tamanho;
    }
    
    public boolean add(Aluno aluno) {
        assegureCapacidade(this.tamanho + 1);
        this.lista[tamanho++] = aluno;
        return true;
    }
    
    public void add(int index, Aluno aluno) {
        if (index < 0 || index > this.tamanho)
            throw new IndexOutOfBoundsException();
        
        assegureCapacidade(this.tamanho + 1);
        
        shiftParaDireita(index);
        
        this.lista[index] = aluno;
        this.tamanho += 1;
        
    }
    
    public void set(int index, Aluno aluno) {
        if (index < 0 || index >= this.tamanho)
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
        
        for (int i = this.tamanho; i > index; i--) {
            this.lista[i] = this.lista[i-1];
        }
    }
    
    public Aluno get(int index) {
        if (index < 0 || index >= this.tamanho)
            throw new IndexOutOfBoundsException();
        return this.lista[index];
    }
    
    public int indexOf(Aluno aluno) {
        for (int i = 0; i < tamanho; i++)
            if (this.lista[i].equals(aluno))
                return i;
        return -1;
    }
    
    public boolean contains(Aluno aluno) {
        return this.indexOf(aluno) != -1;
    }
    
    
    public Aluno remove(int index) {
        if (index < 0 || index >= this.tamanho)
            return null;
        
        Aluno aluno = this.get(index);
        
        shiftParaEsquerda(index);
        this.tamanho -= 1;
        return aluno;
    }
    
    public boolean remove(Aluno aluno) {
        if (aluno == null) return false;
        
        for (int i = 0; i < tamanho; i++) {
            if (this.lista[i].equals(aluno)) {
                this.remove(i);
                return true;
            }
        }
        
        return false;
    }

    private void shiftParaEsquerda(int index) {
        for (int i = index; i < this.tamanho - 1; i++) {
            this.lista[i] = this.lista[i+1];
        }
    }
    
    public String toString() {
        if (isEmpty()) return "[]";
        
        StringBuffer st = new StringBuffer("[");
        for (int i = 0; i < this.tamanho; i++) {
            st.append(this.lista[i].getMatricula() + ",");
        }
        
        st.replace(st.length() - 1, st.length(), "]");
        return st.toString();
    }

}

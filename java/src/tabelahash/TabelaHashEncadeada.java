package tabelahash;
import java.util.ArrayList;
import java.util.Iterator;

public class TabelaHashEncadeada {

	private ArrayList<Aluno>[] tabela;
	private final int CAPACIDADE_DEFAULT = 20;
	
	public TabelaHashEncadeada() {
		this.tabela = new ArrayList[CAPACIDADE_DEFAULT];
	}
	
	public TabelaHashEncadeada(int capacidade) {
		this.tabela = new ArrayList[capacidade];
	}
	
	private int hash(int chave) {
		return chave % this.tabela.length;
	}
	
	public Aluno get(int chave) {
	    int hash = hash(chave);
	    ArrayList<Aluno> alunos = this.tabela[hash];
	    
	    if (alunos == null || alunos.isEmpty()) 
	    		return null;
	    
	    for (Aluno aluno : alunos) {
	    		if (aluno.getMatricula().equals(chave))
	    			return aluno;
	    }
	    
	    return null;
	}  

	public Aluno remove(int chave) {
	    int hash = hash(chave);
	    ArrayList<Aluno> alunos = this.tabela[hash];
	    
	    Iterator<Aluno> it = alunos.iterator();
	    Aluno atual = null;
	    
	    while (it.hasNext()) {
	    		atual = it.next();
	    		if (atual.getMatricula().equals(chave)) {
	    			it.remove();
	    			return atual;
	    		}
	    }
	    
	    return atual;
	}  

}

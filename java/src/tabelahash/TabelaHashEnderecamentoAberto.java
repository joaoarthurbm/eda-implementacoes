package tabelahash;

import java.util.HashSet;
import java.util.Set;

/**
 * Esta classe representa a implementação de uma Tabela Hash que usa endereçamento aberto (sondagem linear)
 * para resolver colisões.
 * 
 * A implementação abaixo apenas lida com objetos do tipo aluno. Essa foi uma decisão para fins didáticos. 
 * Naturalmente, por ser uma estrutura de propósito geral, uma tabela hash deve ser capaz de manipular objetos
 * de qualquer tipo.
 *  
 * @author https://github.com/joaoarthurbm/
 * Computação @ UFCG
 */
public class TabelaHashEnderecamentoAberto {

	private Aluno[] tabela;
	private Set<Integer> chaves;
	private Set<Aluno> valores;
	private int size;
	private double fatorDeCarga;
	
	private static final int CAPACIDADE_DEFAULT = 20;
	private static final double FATOR_DE_CARGA_DEFAULT = 0.75;
	
	public TabelaHashEnderecamentoAberto() {
		this(CAPACIDADE_DEFAULT, FATOR_DE_CARGA_DEFAULT);
	}
	
	public TabelaHashEnderecamentoAberto(int capacidade, double fatorDeCarga) {
		this.tabela = new Aluno[capacidade];
		this.chaves = new HashSet<Integer>();
		this.valores = new HashSet<Aluno>();
		this.fatorDeCarga = fatorDeCarga;
		this.size = 0;
	}
	
	private int hash(Integer chave) {
		return chave % this.tabela.length;
	}
	
	public Set<Integer> getKeys() {
		return this.chaves;
	}
	
	public Set<Aluno> getValue() {
		return this.valores;
	}
	
	public Aluno get(Integer chave) {
		int sondagem = 0;
	    int hash;
	    while (sondagem < tabela.length) {
	    	
	    		hash = (hash(chave) + sondagem) % tabela.length;
	    		
	    		if (tabela[hash] == null) {
	    			return null;
	    		}
 	    		
	    		if (tabela[hash].getMatricula().equals(chave)) {
	    			return tabela[hash];
	    		}
	    		
	    		sondagem += 1;
	    	
	    }

	    return null;
	}  

	public void put(Integer chave, Aluno valor) {
	    
		//TODO: resize
		
	    int sondagem = 0;
	    int hash;
	    while (sondagem < tabela.length) {
	    	
	    		hash = (hash(chave) + sondagem) % tabela.length;
	    		
	    		if (tabela[hash] == null || tabela[hash].getMatricula().equals(chave)) {
	    			tabela[hash] = valor;
	    			this.chaves.add(chave);
	    			this.valores.add(valor);
	    			this.size += 1;
	    			return;
	    		}
	    		
	    		sondagem += 1;
	    	
	    }

	    
	    throw new TabelaCheiaException();

	}  

	public Aluno remove(int chave) {
		int sondagem = 0;
	    int hash;
	    while (sondagem < tabela.length) {
	    	
	    		hash = (hash(chave) + sondagem) % tabela.length;
	    		
	    		if (tabela[hash] != null && tabela[hash].getMatricula().equals(chave)) {
	    			Aluno aluno = tabela[hash];  
	    			tabela[hash] = null;
	    			this.chaves.remove(chave);
	    			this.valores.remove(aluno);
	    			this.size -= 1;
	    			return aluno;
	    		} 
	    		
	    		sondagem += 1;
	    		
	    }
	    
	    return null;
	}
	
	public int size() {
		return this.size;
	}
	
}

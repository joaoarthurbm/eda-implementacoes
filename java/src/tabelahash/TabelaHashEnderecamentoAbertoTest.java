package tabelahash;

import static org.junit.Assert.*;

import org.junit.Test;

public class TabelaHashEnderecamentoAbertoTest {

	@Test
	public void test() {
		TabelaHashEnderecamentoAberto tabela = new TabelaHashEnderecamentoAberto(5);
		
		tabela.put(0, new Aluno(0, "a"));
		tabela.put(1, new Aluno(1, "b"));
		tabela.put(2, new Aluno(2, "c"));
		tabela.put(3, new Aluno(3, "d"));
		tabela.put(4, new Aluno(4, "e"));
		
		// testando substituição
		tabela.put(0, new Aluno(0, "aa"));
		assertTrue(tabela.get(0).getNome().equals("aa"));
		tabela.put(4, new Aluno(4, "ee"));
		assertTrue(tabela.get(4).getNome().equals("ee"));
		
		// testando tabela cheia
		try {
			tabela.put(14, new Aluno(14, "nao pode entrar na tabela"));
			fail("não pode executar essa linha.");
		} catch (TabelaCheiaException e) {}
		
		// testando sondagem
		tabela = new TabelaHashEnderecamentoAberto(5);
		tabela.put(0, new Aluno(0, "a"));
		tabela.put(5, new Aluno(5, "b"));
		tabela.put(10, new Aluno(10, "c"));
		tabela.put(15, new Aluno(15, "d"));
		tabela.put(20, new Aluno(20, "e"));
	
		assertTrue(tabela.get(0).getNome().equals("a"));
		assertTrue(tabela.get(5).getNome().equals("b"));
		assertTrue(tabela.get(10).getNome().equals("c"));
		assertTrue(tabela.get(15).getNome().equals("d"));
		assertTrue(tabela.get(20).getNome().equals("e"));
			
	}
	
}

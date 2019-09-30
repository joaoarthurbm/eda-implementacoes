package arraylist;

import static org.junit.Assert.*;

import org.junit.Test;

import util.Aluno;

public class ArrayListTest {

	@Test
	public void test() {
		
		ArrayList lista = new ArrayList(5);
		assertTrue(lista.isEmpty());
		
		assertTrue(lista.add(new Aluno(3, "João")));
		assertEquals(1, lista.size());
		assertTrue(lista.add(new Aluno(13, "Maria")));
		assertTrue(lista.add(new Aluno(9, "Clara")));
		assertTrue(lista.add(new Aluno(7, "Lívia")));
		assertTrue(lista.add(new Aluno(40, "Marielle")));
		assertEquals(5, lista.size());
		
		assertEquals(3, (int)lista.get(0).getMatricula());
		assertEquals(13, (int)lista.get(1).getMatricula());
		assertEquals(9, (int)lista.get(2).getMatricula());
		assertEquals(7, (int)lista.get(3).getMatricula());
		assertEquals(40, (int)lista.get(4).getMatricula());
		
		// acessando posição inválida.
		try {
			lista.get(-1);
			fail("esta linha não pode ser executada.");
		} catch (IndexOutOfBoundsException e){}
		
		try {
			lista.get(5);
			fail("esta linha não pode ser executada.");
		} catch (IndexOutOfBoundsException e){}
		
		// removendo posição inválida
		lista.remove(-1);
		lista.remove(5);
		
		//removendo o último elemento
		lista.remove(lista.size() - 1);
		assertEquals(4, lista.size());
		assertEquals(3, (int)lista.get(0).getMatricula());
		assertEquals(13, (int)lista.get(1).getMatricula());
		assertEquals(9, (int)lista.get(2).getMatricula());
		assertEquals(7, (int)lista.get(3).getMatricula());
		
		
		// removendo o primeiro elemento
		lista.remove(0);
		assertEquals(3, lista.size());
		assertEquals(13, (int)lista.get(0).getMatricula());
		assertEquals(9, (int)lista.get(1).getMatricula());
		assertEquals(7, (int)lista.get(2).getMatricula());
		
		// removendo um elemento central
		lista.remove(1);
		assertEquals(2, lista.size());
		assertEquals(13, (int)lista.get(0).getMatricula());
		assertEquals(7, (int)lista.get(1).getMatricula());
		
		
		// removendo todos
		lista.remove(0);
		lista.remove(0);
		assertTrue(lista.isEmpty());
		
		// forçando resize
		assertTrue(lista.add(new Aluno(3, "João")));
		assertTrue(lista.add(new Aluno(13, "Maria")));
		assertTrue(lista.add(new Aluno(9, "Clara")));
		assertTrue(lista.add(new Aluno(7, "Lívia")));
		assertTrue(lista.add(new Aluno(40, "Marielle")));
		assertTrue(lista.add(new Aluno(86, "Amarildo")));
		
		assertEquals(6, lista.size());
		
		// testando set com índice inválido
		try {
			lista.set(lista.size(), new Aluno(444, "Aluno inválido"));
			fail("esta linha não pode ser executada.");
		} catch (IndexOutOfBoundsException e){}
		try {
			lista.set(-1, new Aluno(444, "Aluno inválido"));
			fail("esta linha não pode ser executada.");
		} catch (IndexOutOfBoundsException e){}
	
		// testando set com índice válido
		lista.set(lista.size() - 1, new Aluno(999, "Carlos"));
		assertEquals(lista.get(lista.size() - 1), new Aluno(999, "Carlos"));
		
		lista.set(0, new Aluno(638, "Guilherme"));
		assertEquals(lista.get(0), new Aluno(638, "Guilherme"));
		
		lista.set(2, new Aluno(90, "Júlia"));
		assertEquals(lista.get(2), new Aluno(90, "Júlia"));

	}
	
}
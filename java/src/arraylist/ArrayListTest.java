package arraylist;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayListTest {

	@Test
	public void test() {
		
		ArrayList lista = new ArrayList(5);
		assertTrue(lista.isEmpty());
		
		lista.add(3);
		assertEquals(1, lista.size());
		assertTrue(lista.add(13));
		assertTrue(lista.add(9));
		assertTrue(lista.add(7));
		assertTrue(lista.add(-4));
		assertEquals(5, lista.size());
		
		assertEquals(3, lista.get(0));
		assertEquals(13, lista.get(1));
		assertEquals(9, lista.get(2));
		assertEquals(7, lista.get(3));
		assertEquals(-4, lista.get(4));
		
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
		assertFalse(lista.remove(-1));
		assertFalse(lista.remove(5));
		
		//removendo o último elemento
		assertTrue(lista.remove(lista.size() - 1));
		assertEquals(4, lista.size());
		assertEquals(3, lista.get(0));
		assertEquals(13, lista.get(1));
		assertEquals(9, lista.get(2));
		assertEquals(7, lista.get(3));
		
		
		// removendo o primeiro elemento
		assertTrue(lista.remove(0));
		assertEquals(3, lista.size());
		assertEquals(13, lista.get(0));
		assertEquals(9, lista.get(1));
		assertEquals(7, lista.get(2));
		
		// removendo um elemento central
		assertTrue(lista.remove(1));
		assertEquals(2, lista.size());
		assertEquals(13, lista.get(0));
		assertEquals(7, lista.get(1));
		
		// removendo todos
		lista.remove(0);
		lista.remove(0);
		assertTrue(lista.isEmpty());
		
		// forçando resize
		assertTrue(lista.add(3));
		assertTrue(lista.add(13));
		assertTrue(lista.add(9));
		assertTrue(lista.add(7));
		assertTrue(lista.add(-4));
		assertTrue(lista.add(-4849));
		assertEquals(6, lista.size());
		assertEquals("[3,13,9,7,-4,-4849]", lista.toString());
	}

}

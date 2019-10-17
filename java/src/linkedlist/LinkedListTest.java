package linkedlist;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import util.Aluno;


public class LinkedListTest {

	@Before
	public void setup() {
	}
	
	@Test
	public void testAddRemoveLast() {
		
		// lista vazia
		LinkedList lista = new LinkedList();
		assertTrue(lista.isEmpty());
		assertEquals(0, lista.size());
		
		try {
			lista.getLast();
			fail("esta linha não pode ser executada.");
		} catch (NoSuchElementException e){}
		
		
		// lista: [1]
		lista.addLast(new Aluno(1, "Marielle"));
		assertFalse(lista.isEmpty());
		assertEquals(1, lista.size());
		assertEquals(1, (int)lista.getFirst().getMatricula());
		assertEquals(1, (int)lista.getLast().getMatricula());
		
		// lista: [1, 2]
		lista.addLast(new Aluno(2, "Amarildo"));
		assertEquals(1, (int)lista.getFirst().getMatricula());
		assertEquals(2, (int)lista.getLast().getMatricula());
		assertEquals(2, lista.size());
		
		// lista: [1, 2, 9, 11]
		lista.addLast(new Aluno(9, "Renan"));
		lista.addLast(new Aluno(11, "Bethania"));
		assertEquals(1, (int)lista.getFirst().getMatricula());
		assertEquals(11, (int)lista.getLast().getMatricula());
		assertEquals(4, lista.size());
				
		// removendo. lista: [1, 2, 9]
		assertEquals((int)lista.removeLast().getMatricula(), 11);
		assertEquals(1, (int)lista.getFirst().getMatricula());
		assertEquals(9, (int)lista.getLast().getMatricula());
		assertEquals(3, lista.size());
		
		// removendo. lista: [1]
		assertEquals((int)lista.removeLast().getMatricula(), 9);
		assertEquals((int)lista.removeLast().getMatricula(), 2);
		assertEquals(1, (int)lista.getFirst().getMatricula());
		assertEquals(1, (int)lista.getLast().getMatricula());
		
		// removendo. lista: []
		assertEquals((int)lista.removeLast().getMatricula(), 1);
		assertTrue(lista.isEmpty());
		assertEquals(0, lista.size());
		
		// testando novamente após lista vazia
		// lista: [1]
		lista.addLast(new Aluno(1, "Marielle"));
		assertFalse(lista.isEmpty());
		assertEquals(1, lista.size());
		assertEquals(1, (int)lista.getFirst().getMatricula());
		assertEquals(1, (int)lista.getLast().getMatricula());
		
		// lista: [1, 2]
		lista.addLast(new Aluno(2, "Amarildo"));
		assertEquals(1, (int)lista.getFirst().getMatricula());
		assertEquals(2, (int)lista.getLast().getMatricula());
		
		// lista: [1, 2, 9, 11]
		lista.addLast(new Aluno(9, "Renan"));
		lista.addLast(new Aluno(11, "Bethania"));
		assertEquals(1, (int)lista.getFirst().getMatricula());
		assertEquals(11, (int)lista.getLast().getMatricula());
	}
	
	@Test
	public void testAddRemoveFirst() {
		
		LinkedList lista = new LinkedList();
		
		// no such element
		try {
			lista.getFirst();
			fail("esta linha não pode ser executada.");
		} catch (NoSuchElementException e){}
		
		
		// lista: [1]
		lista.addFirst(new Aluno(1, "Marielle"));
		assertFalse(lista.isEmpty());
		assertEquals(1, lista.size());
		assertEquals(1, (int)lista.getFirst().getMatricula());
		
		// lista: [2, 1]
		lista.addFirst(new Aluno(2, "Amarildo"));
		assertEquals(2, (int)lista.getFirst().getMatricula());
		assertEquals(1, (int)lista.getLast().getMatricula());
		
		// lista: [11, 9, 2, 1]
		lista.addFirst(new Aluno(9, "Renan"));
		lista.addFirst(new Aluno(11, "Bethania"));
		assertEquals(11, (int)lista.getFirst().getMatricula());
		assertEquals(1, (int)lista.getLast().getMatricula());
				
		// removendo. lista: [9, 2, 1]
		assertEquals((int)lista.removeFirst().getMatricula(), 11);
		assertEquals(9, (int)lista.getFirst().getMatricula());
		assertEquals(1, (int)lista.getLast().getMatricula());
		
		// removendo. lista: [1]
		assertEquals((int)lista.removeFirst().getMatricula(), 9);
		assertEquals((int)lista.removeFirst().getMatricula(), 2);
		assertEquals(1, (int)lista.getFirst().getMatricula());
		assertEquals(1, (int)lista.getLast().getMatricula());
		assertTrue(lista.size() == 1);
		
		// removendo. lista: []
		assertEquals((int)lista.removeFirst().getMatricula(), 1);
		assertTrue(lista.isEmpty());
		assertTrue(lista.size() == 0);
		
		// testando novamente após lista vazia
		// lista: [1]
		lista.addFirst(new Aluno(1, "Marielle"));
		assertFalse(lista.isEmpty());
		assertEquals(1, lista.size());
		assertEquals(1, (int)lista.getFirst().getMatricula());
		
		// lista: [2, 1]
		lista.addFirst(new Aluno(2, "Amarildo"));
		assertEquals(2, (int)lista.getFirst().getMatricula());
		assertEquals(1, (int)lista.getLast().getMatricula());
		
		// lista: [11, 9, 2, 1]
		lista.addFirst(new Aluno(9, "Renan"));
		lista.addFirst(new Aluno(11, "Bethania"));
		assertEquals(11, (int)lista.getFirst().getMatricula());
		assertEquals(1, (int)lista.getLast().getMatricula());
	}
	
	@Test
	public void testAddGet() {
		LinkedList lista = new LinkedList();
		
		
		// índices inválidos.
		try {
			lista.addR(1, new Aluno(1, "Marielle"));
			fail("esta linha não pode ser executada.");
		} catch (IndexOutOfBoundsException e){}
		
		try {
			lista.addR(0, new Aluno(1, "Marielle"));
			fail("esta linha não pode ser executada.");
		} catch (IndexOutOfBoundsException e){}
		
		try {
			lista.addR(-1, new Aluno(1, "Mariella"));
			fail("esta linha não pode ser executada.");
		} catch (IndexOutOfBoundsException e){}
		
		
		// em head
		lista.addLast(new Aluno(1, "Marielle"));
		lista.addR(0, new Aluno(2, "Amarildo"));
		assertEquals(2, (int)lista.getFirst().getMatricula());
		assertEquals(1, (int)lista.getLast().getMatricula());
		assertEquals(2, (int)lista.get(0).getMatricula());
		lista.addR(0, new Aluno(3, "Renan"));
		assertEquals(3, (int)lista.getFirst().getMatricula());
		assertEquals(1, (int)lista.getLast().getMatricula());
		assertEquals(3, (int)lista.get(0).getMatricula());
		assertEquals(2, (int)lista.get(1).getMatricula());
		assertEquals(1, (int)lista.get(2).getMatricula());
		
		// no meio
		lista.addR(1, new Aluno(4, "Bethania"));
		assertEquals(3, (int)lista.get(0).getMatricula());
		assertEquals(4, (int)lista.get(1).getMatricula());
		assertEquals(2, (int)lista.get(2).getMatricula());
		assertEquals(1, (int)lista.get(3).getMatricula());
		assertEquals(3, (int)lista.getFirst().getMatricula());
		assertEquals(1, (int)lista.getLast().getMatricula());
		
		// em tail
		lista.addR(3, new Aluno(5, "Caetano"));
		assertEquals(3, (int)lista.getFirst().getMatricula());
		assertEquals(5, (int)lista.getLast().getMatricula());
		
		// contains e indexOf
		assertTrue(lista.contains(new Aluno(5, "Caetano")));
		assertEquals(-1, lista.indexOf(new Aluno(404, "")));
		assertEquals(0, lista.indexOf(new Aluno(3, "Renan")));
		assertEquals(1, lista.indexOf(new Aluno(4, "Bethania")));
		assertEquals(2, lista.indexOf(new Aluno(2, "Amarildo")));
		assertEquals(3, lista.indexOf(new Aluno(1, "Marielle")));
		assertEquals(4, lista.indexOf(new Aluno(5, "Caetano")));
		
	}
	
	@Test
	public void testRemove() {
		LinkedList lista = new LinkedList();
		
		
		// índices inválidos.
		try {
			lista.remove(0);
			fail("esta linha não pode ser executada.");
		} catch (IndexOutOfBoundsException e){}
		
		try {
			lista.remove(1);
			fail("esta linha não pode ser executada.");
		} catch (IndexOutOfBoundsException e){}
		
		try {
			lista.remove(-1);
			fail("esta linha não pode ser executada.");
		} catch (IndexOutOfBoundsException e){}
		
		// aluno não está na lista e a lista está vazia
		assertFalse(lista.remove(new Aluno(1, "Marielle")));
		
		Aluno marielle = new Aluno(1, "Marielle");
		Aluno amarildo = new Aluno(2, "Amarildo");
		Aluno renan = new Aluno(3, "Renan");
		Aluno bethania = new Aluno(4, "Bethania");
		Aluno caetano = new Aluno(5, "Caetano");
		
		lista.addLast(marielle);
		// aluno não está na lista
		assertFalse(lista.remove(new Aluno(404, "")));
		
		lista.addLast(amarildo);
		lista.addLast(renan);
		lista.addLast(bethania);
		lista.addLast(caetano);

		assertTrue(lista.remove(marielle));
		assertFalse(lista.contains(marielle));
		assertEquals(new Aluno(2, "Amarildo"), lista.getFirst());
		
		assertTrue(lista.remove(caetano));
		assertFalse(lista.contains(caetano));
		assertEquals(new Aluno(4, "Bethania"), lista.getLast());
		
		assertTrue(lista.remove(renan));
		assertFalse(lista.contains(renan));
		assertEquals(new Aluno(4, "Bethania"), lista.getLast());
		assertEquals(2, lista.size());
		
		// limpando
		lista.remove(bethania);
		assertEquals(new Aluno(2, "Amarildo"), lista.getLast());
		assertEquals(new Aluno(2, "Amarildo"), lista.getLast());
		lista.remove(amarildo);
		
		assertTrue(lista.isEmpty());
		
		// adicionando novamente
		lista.addLast(marielle);
		lista.addLast(amarildo);
		lista.addLast(renan);
		lista.addLast(bethania);
		lista.addLast(caetano);
		
		// removendo o primeiro
		lista.remove(0);
		assertFalse(lista.contains(marielle));
		assertEquals(new Aluno(2, "Amarildo"), lista.getFirst());
		
		// removendo o último
		lista.remove(lista.size() - 1);
		assertFalse(lista.contains(caetano));
		assertEquals(new Aluno(4, "Bethania"), lista.getLast());
		
		// removendo o último
		lista.remove(1);
		assertFalse(lista.contains(renan));
		assertEquals(new Aluno(2, "Amarildo"), lista.getFirst());
		assertEquals(new Aluno(4, "Bethania"), lista.getLast());
		
		// limpando
		lista.remove(0);
		lista.remove(0);
		
		assertTrue(lista.isEmpty());
		assertTrue(lista.size() == 0);
		
		try {
			lista.getFirst();
			System.out.println("esta linha não pode ser executada.");
		} catch(NoSuchElementException e){}
		
		try {
			lista.getLast();
			System.out.println("esta linha não pode ser executada.");
		} catch(NoSuchElementException e){}
		
		
		
		
	}
	
}
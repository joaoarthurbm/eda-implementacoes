package bst;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class BSTTest {

	@Test
	public void testRemove() {
		BST bst = new BST();
		bst.add(85);
		bst.add(49);
		bst.add(97);
		bst.add(7);
		bst.add(53);
		bst.add(93);
		bst.add(51);
		bst.add(81);
		bst.add(65);
		bst.add(55);
		
		ArrayList<Integer> expectedBFS = new ArrayList<Integer>();
		expectedBFS.add(85);
		expectedBFS.add(49);
		expectedBFS.add(97);
		expectedBFS.add(7);
		expectedBFS.add(53);
		expectedBFS.add(93);
		expectedBFS.add(51);
		expectedBFS.add(81);
		expectedBFS.add(65);
		expectedBFS.add(55);
		
		assertEquals(expectedBFS, bst.bfs());
		
		bst.remove(85);
		expectedBFS = new ArrayList<Integer>();
		expectedBFS.add(93);
		expectedBFS.add(49);
		expectedBFS.add(97);
		expectedBFS.add(7);
		expectedBFS.add(53);
		expectedBFS.add(51);
		expectedBFS.add(81);
		expectedBFS.add(65);
		expectedBFS.add(55);
		assertEquals(expectedBFS, bst.bfs());
		
		bst.remove(93);
		expectedBFS = new ArrayList<Integer>();
		expectedBFS.add(97);
		expectedBFS.add(49);
		expectedBFS.add(7);
		expectedBFS.add(53);
		expectedBFS.add(51);
		expectedBFS.add(81);
		expectedBFS.add(65);
		expectedBFS.add(55);
		assertEquals(expectedBFS, bst.bfs());
		
		bst.remove(7);
		expectedBFS = new ArrayList<Integer>();
		expectedBFS.add(97);
		expectedBFS.add(49);
		expectedBFS.add(53);
		expectedBFS.add(51);
		expectedBFS.add(81);
		expectedBFS.add(65);
		expectedBFS.add(55);
		assertEquals(expectedBFS, bst.bfs());
		
		bst.remove(97);
		expectedBFS = new ArrayList<Integer>();
		expectedBFS.add(49);
		expectedBFS.add(53);
		expectedBFS.add(51);
		expectedBFS.add(81);
		expectedBFS.add(65);
		expectedBFS.add(55);
		assertEquals(expectedBFS, bst.bfs());
		
		bst.remove(53);
		expectedBFS = new ArrayList<Integer>();
		expectedBFS.add(49);
		expectedBFS.add(55);
		expectedBFS.add(51);
		expectedBFS.add(81);
		expectedBFS.add(65);
		assertEquals(expectedBFS, bst.bfs());
		assertEquals(5, bst.size());

		bst.remove(81);
		expectedBFS = new ArrayList<Integer>();
		expectedBFS.add(49);
		expectedBFS.add(55);
		expectedBFS.add(51);
		expectedBFS.add(65);
		assertEquals(expectedBFS, bst.bfs());
		
		bst.remove(51);
		expectedBFS = new ArrayList<Integer>();
		expectedBFS.add(49);
		expectedBFS.add(55);
		expectedBFS.add(65);
		assertEquals(expectedBFS, bst.bfs());
		
		bst.remove(55);
		expectedBFS = new ArrayList<Integer>();
		expectedBFS.add(49);
		expectedBFS.add(65);
		assertEquals(expectedBFS, bst.bfs());
		
		bst.remove(49);
		expectedBFS = new ArrayList<Integer>();
		expectedBFS.add(65);
		assertEquals(expectedBFS, bst.bfs());
		
		bst.remove(65);
		expectedBFS = new ArrayList<Integer>();
		assertEquals(expectedBFS, bst.bfs());
		
		bst.add(65);
		expectedBFS = new ArrayList<Integer>();
		expectedBFS.add(65);
		assertEquals(expectedBFS, bst.bfs());
		assertEquals(bst.size(), 1);

	}
}

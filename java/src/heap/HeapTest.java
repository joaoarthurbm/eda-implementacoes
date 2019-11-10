package heap;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class HeapTest {

	
	
	@Test
	public void testLeft() {
		// 100, 90, 85, 30, 45, 60, 70, 20
		Heap heap = new Heap(15);
		heap.add(100);
		heap.add(90);
		heap.add(85);
		heap.add(30);
		heap.add(45);
		heap.add(60);
		heap.add(70);
		heap.add(20);
		assertEquals(heap.left(0), 1);
		assertEquals(heap.left(2), 5);
		assertEquals(heap.left(1), 3);
			
	}

	@Test
	public void testRight() {
		// 100, 90, 85, 30, 45, 60, 70, 20
		Heap heap = new Heap(15);
		heap.add(100);
		heap.add(90);
		heap.add(85);
		heap.add(30);
		heap.add(45);
		heap.add(60);
		heap.add(70);
		heap.add(20);
		heap.add(113);
		assertEquals(heap.right(0), 2);
		assertEquals(heap.right(2), 6);
		assertEquals(heap.right(1), 4);	
	}

	@Test
	public void testParent() {
		// 100, 90, 85, 30, 45, 60, 70, 20
		Heap heap = new Heap(15);
		heap.add(100);
		heap.add(90);
		heap.add(85);
		heap.add(30);
		heap.add(45);
		heap.add(60);
		heap.add(70);
		heap.add(20);
		assertEquals(heap.parent(5), 2);
		assertEquals(heap.parent(3), 1);
		assertEquals(heap.parent(4), 1);	
	}
	
	@Test
	public void testRemove() {
		int[] expected = new int[]{82, 65, 62, 45, 56, 52, 43, 30, 33, 38,
				0, 0, 0, 0, 0};
		
		Heap heap = new Heap(15);
		for (int i = 0; i <= 9; i++)
			heap.add(expected[i]);
		assertEquals(Arrays.toString(expected), heap.toString());
		
		assertEquals(82, heap.remove());
		expected = new int[]{65, 56, 62, 45, 38, 52, 43, 30, 33, 38, 0, 0, 0, 0, 0};
		assertEquals(Arrays.toString(expected), heap.toString());
		
		assertEquals(65, heap.remove());
		expected = new int[]{62, 56, 52, 45, 38, 33, 43, 30, 33, 38, 0, 0, 0, 0, 0};
		assertEquals(Arrays.toString(expected), heap.toString());
		
		assertEquals(62, heap.remove());
		expected = new int[]{56, 45, 52, 30, 38, 33, 43, 30, 33, 38, 0, 0, 0, 0, 0};
		assertEquals(Arrays.toString(expected), heap.toString());
		
		assertEquals(56, heap.remove());
		expected = new int[]{52, 45, 43, 30, 38, 33, 43, 30, 33, 38, 0, 0, 0, 0, 0};
		assertEquals(Arrays.toString(expected), heap.toString());
		
		assertEquals(52, heap.remove());
		expected = new int[]{45, 38, 43, 30, 33, 33, 43, 30, 33, 38, 0, 0, 0, 0, 0};
		assertEquals(Arrays.toString(expected), heap.toString());
		
		assertEquals(45, heap.remove());
		expected = new int[]{43, 38, 33, 30, 33, 33, 43, 30, 33, 38, 0, 0, 0, 0, 0};
		assertEquals(Arrays.toString(expected), heap.toString());
		
		assertEquals(43, heap.remove());
		expected = new int[]{38, 30, 33, 30, 33, 33, 43, 30, 33, 38, 0, 0, 0, 0, 0};
		assertEquals(Arrays.toString(expected), heap.toString());
		
		assertEquals(38, heap.remove());
		expected = new int[]{33, 30, 33, 30, 33, 33, 43, 30, 33, 38, 0, 0, 0, 0, 0};
		assertEquals(Arrays.toString(expected), heap.toString());
		
		assertEquals(33, heap.remove());
		expected = new int[]{30, 30, 33, 30, 33, 33, 43, 30, 33, 38, 0, 0, 0, 0, 0};
		assertEquals(Arrays.toString(expected), heap.toString());
		
		assertEquals(30, heap.remove());
		expected = new int[]{30, 30, 33, 30, 33, 33, 43, 30, 33, 38, 0, 0, 0, 0, 0};
		assertEquals(Arrays.toString(expected), heap.toString());
		
		
		
	}

}

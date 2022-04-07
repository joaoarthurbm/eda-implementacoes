import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

public class TestAVL {

    @Test
    public void testAddIterative() {
        AVL avl = new AVL();

        avl.add(50);
        avl.add(44);
        avl.add(30);
        avl.add(60);
        avl.add(55);
        avl.add(20);
        avl.add(25);
        avl.add(64);
        avl.add(72);

        ArrayList<Integer> expectedBFSList = new ArrayList<>(Arrays.asList(44, 25, 55, 20, 30, 50, 64, 60, 72));

        assertEquals(expectedBFSList, avl.bfs());

        avl.add(99);
        avl.add(100);

        expectedBFSList = new ArrayList<>(Arrays.asList(44, 25, 64, 20, 30, 55, 99, 50, 60, 72, 100));
        assertEquals(expectedBFSList, avl.bfs());
    }

    @Test
    public void testAddRecursive() {
        AVL avl = new AVL();

        avl.recursiveAdd(50);
        avl.recursiveAdd(44);
        avl.recursiveAdd(30);
        avl.recursiveAdd(60);
        avl.recursiveAdd(55);
        avl.recursiveAdd(20);
        avl.recursiveAdd(25);
        avl.recursiveAdd(64);
        avl.recursiveAdd(72);

        ArrayList<Integer> expectedBFSList = new ArrayList<>(Arrays.asList(44, 25, 55, 20, 30, 50, 64, 60, 72));

        assertEquals(expectedBFSList, avl.bfs());

        avl.recursiveAdd(99);
        avl.recursiveAdd(100);

        expectedBFSList = new ArrayList<>(Arrays.asList(44, 25, 64, 20, 30, 55, 99, 50, 60, 72, 100));
        assertEquals(expectedBFSList, avl.bfs());
    }

    @Test
    public void testLinearAdd() {
        AVL avl = new AVL();

        avl.add(1);
        avl.add(2);
        avl.add(3);
        avl.add(4);
        avl.add(5);
        avl.add(6);
        avl.add(7);

        ArrayList<Integer> expectedBFSList = new ArrayList<>(Arrays.asList(4, 2, 6, 1, 3, 5, 7));

        assertEquals(expectedBFSList, avl.bfs());
    }

    @Test
    public void testLinearRecursiveAdd() {
        AVL avl = new AVL();

        avl.recursiveAdd(1);
        avl.recursiveAdd(2);
        avl.recursiveAdd(3);
        avl.recursiveAdd(4);
        avl.recursiveAdd(5);
        avl.recursiveAdd(6);
        avl.recursiveAdd(7);

        ArrayList<Integer> expectedBFSList = new ArrayList<>(Arrays.asList(4, 2, 6, 1, 3, 5, 7));

        assertEquals(expectedBFSList, avl.bfs());
    }
}
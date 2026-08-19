import java.util.ArrayList;
import java.util.List;

public class BTreeTest {

    public static void main(String[] args) {

        testArvoreVazia();
        testInsertSearch();
        testInsertComMultiplosValores();
        testMinMax();
        testRemove();
        testHeight();
        testBreadthFS();
        testDepthFS();

        System.out.println("Todos os testes passaram.");
    }

    private static void testArvoreVazia() {
        BTree tree = new BTree(4);

        assert tree.isEmpty();
        assert tree.size() == 0;
        assert tree.height() == 0;

        assert tree.search(10).getValue() == null;

        assert tree.min().getValue() == null;
        assert tree.max().getValue() == null;
    }

    private static void testInsertSearch() {

        BTree tree = new BTree(4);

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);

        assert !tree.isEmpty();
        assert tree.size() == 3;
        assert tree.height() == 1;

        assert tree.search(10).getValue() == 10;
        assert tree.search(20).getValue() == 20;
        assert tree.search(30).getValue() == 30;
        assert tree.search(40).getValue() == null;

        tree.insert(40);

        assert tree.size() == 4;
        assert tree.height() == 2;

        assert tree.search(10).getValue() == 10;
        assert tree.search(20).getValue() == 20;
        assert tree.search(30).getValue() == 30;
        assert tree.search(40).getValue() == 40;
    }

    private static void testInsertComMultiplosValores() {

        BTree tree = new BTree(4);

        int[] valores = {50,10,30,20,40,60};

        for (int v : valores)
            tree.insert(v);

        assert tree.size() == 6;

        for (int v : valores)
            assert tree.search(v).getValue() == v;

        assert tree.search(99).getValue() == null;
    }

    private static void testMinMax() {

        BTree tree = new BTree(4);

        int[] valores = {40,10,80,5,90,15,50};

        for (int v : valores)
            tree.insert(v);

        assert tree.min().getValue() == 5;
        assert tree.max().getValue() == 90;
    }

    private static void testRemove() {

        BTree tree = new BTree(4);

        int[] valores = {40,20,60,10,30,50,70};

        for (int v : valores)
            tree.insert(v);

        assert tree.size() == 7;

        tree.remove(10);

        assert tree.size() == 6;
        assert tree.search(10).getValue() == null;

        tree.remove(70);

        assert tree.size() == 5;
        assert tree.search(70).getValue() == null;

        tree.remove(40);

        assert tree.size() == 4;
        assert tree.search(40).getValue() == null;

        assert tree.min().getValue() == 20;
        assert tree.max().getValue() == 60;

        tree.remove(999);

        assert tree.size() == 4;
    }

    private static void testHeight() {

        BTree tree = new BTree(4);

        assert tree.height() == 0;

        tree.insert(10);
        assert tree.height() == 1;

        tree.insert(20);
        tree.insert(30);
        tree.insert(40);

        assert tree.height() == 2;
    }

    private static void testBreadthFS() {
        BTree tree = new BTree(4);
        int[] valores = {40,20,60,10,30,50,70};
        for (int v : valores)
            tree.insert(v);

        ArrayList<BNode> bfs = tree.breadthFS();

        //3 nos: raiz + 2 filhos
        assert bfs.size() == 3;

        //nivel 0: raiz com a chave [40]
        assert bfs.get(0) == tree.getRoot();
        assert bfs.get(0).keys.equals(new ArrayList<>(List.of(40)));

        //nivel 1: filho esquerdo [10,20,30], direito [50,60,70]
        assert bfs.get(1).keys.equals(new ArrayList<>(List.of(10,20,30)));
        assert bfs.get(2).keys.equals(new ArrayList<>(List.of(50,60,70)));
    }

    private static void testDepthFS() {
        BTree tree = new BTree(4);
        int[] valores = {40,20,60,10,30,50,70};
        for (int v : valores)
            tree.insert(v);

        ArrayList<BNode> dfs = tree.depthFS();

        //deve visitar exatamente 3 nos
        assert dfs.size() == 3;

        //preordem: raiz primeiro
        assert dfs.get(0) == tree.getRoot();
        assert dfs.get(0).keys.equals(new ArrayList<>(List.of(40)));

        //filho esquerdo antes do direito
        assert dfs.get(1).keys.equals(new ArrayList<>(List.of(10,20,30)));
        assert dfs.get(2).keys.equals(new ArrayList<>(List.of(50,60,70)));
    }
}

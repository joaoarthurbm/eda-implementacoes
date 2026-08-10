import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestPV {

    public static void main(String[] args) {
        TestPV t = new TestPV();

        t.testAdd();
        t.testLinearAdd();
        t.testZigZagLeftInsertion();
        t.testZigZagRightInsertion();
        t.testSearch();
        t.testRemove();
        t.testComplexRemove();
        t.testInsertionAndRemove();
        t.testBlackHeight();
        t.testBlackHeightAfterInsertions();
        t.testBlackHeightAfterRemovals();

        System.out.println("PASSOU NOS TESTES!!!");
    }

    public void testAdd() {
        PV pv = new PV();

        pv.add(10);
        assert pv.isEmpty() == false;
        assert pv.size() == 1;
        assert pv.validateBlackHeight() == 1;

        pv.add(5);
        pv.add(15);

        assert pv.size() == 3;
        assert pv.validateBlackHeight() != -1;

        ArrayList<Integer> v = new ArrayList<>(Arrays.asList(10, 5, 15));
        assert pv.bfs().equals(v);
    }

    public void testLinearAdd() {
        PV pv = new PV();

        pv.add(1);
        pv.add(2);
        pv.add(3);
        pv.add(4);
        pv.add(5);
        pv.add(6);
        pv.add(7);

        assert pv.size() == 7;
        assert pv.validateBlackHeight() == 2;
        
    }

    public void testZigZagLeftInsertion() {
        PV pv = new PV();
        
        pv.add(30);
        pv.add(10);
        pv.add(20);

        ArrayList<Integer> v = new ArrayList<>(Arrays.asList(20, 10, 30));
        assert pv.bfs().equals(v);
        assert pv.validateBlackHeight() == 1;
    }

    public void testZigZagRightInsertion() {
        PV pv = new PV();

        pv.add(10);
        pv.add(30);
        pv.add(20);

        ArrayList<Integer> v = new ArrayList<>(Arrays.asList(20, 10, 30));
        assert pv.bfs().equals(v);
        assert pv.validateBlackHeight() == 1;
    }

    public void testSearch() {
        PV pv = new PV();

        pv.add(10);
        pv.add(20);
        pv.add(30);

        assert pv.search(20) != null;
        assert pv.search(20).value == 20;
        
        assert pv.search(999) == pv.search(888);
        assert pv.search(999) == pv.search(5);
        assert pv.search(999) == pv.search(15);
    }

    public void testRemove() {
        PV pv = new PV();

        pv.add(50);
        pv.add(25);
        pv.add(75);
        pv.add(12);
        pv.add(37);

        assert pv.size() == 5;

        pv.remove(12);
        assert pv.size() == 4;
        assert pv.validateBlackHeight() == 2;

        pv.remove(50);
        assert pv.size() == 3;
        assert pv.validateBlackHeight() == 2;
        
        assert pv.search(37).value == 37;
        assert pv.search(25).value == 25;
        assert pv.search(75).value == 75;
    }

    public void testComplexRemove() {
        PV pv = new PV();

        int[] valores = {41, 38, 31, 12, 19, 8, 20, 27};
        for (int v : valores) pv.add(v);

        pv.remove(41);
        assert pv.search(999) == pv.search(41);
        assert pv.validateBlackHeight() == 2;

        pv.remove(27);
        assert pv.search(999) == pv.search(27);
        assert pv.validateBlackHeight() == 2;

        assert pv.size() == 6;
    }

    public void testInsertionAndRemove() {
        PV pv = new PV();

        pv.add(20);
        pv.add(10);
        pv.add(30);
        assert pv.validateBlackHeight() == 1;
        assert pv.bfs().equals(List.of(20, 10, 30));

        pv.add(40);
        assert pv.validateBlackHeight() == 2;
        assert pv.bfs().equals(List.of(20, 10, 30, 40));

        pv.add(50);
        pv.add(45);
        assert pv.validateBlackHeight() == 2;
        assert pv.bfs().equals(List.of(20, 10, 40, 30, 50, 45));

        pv.add(48);
        pv.remove(10);
        assert pv.validateBlackHeight() == 2;
        assert pv.bfs().equals(List.of(40, 20, 48, 30, 45, 50));

        pv.remove(45);
        pv.remove(50);
        assert pv.validateBlackHeight() == 2;
        assert pv.bfs().equals(List.of(40, 20, 48, 30));

        pv.remove(48);
        assert pv.validateBlackHeight() == 2;
        assert pv.bfs().equals(List.of(30, 20, 40));
    }

    public void testBlackHeightAfterInsertions() {
        PV pv = new PV();

        pv.add(10);
        assert pv.validateBlackHeight() == 1;

        pv.add(20);
        assert pv.validateBlackHeight() == 1;

        pv.add(30);
        assert pv.validateBlackHeight() == 1;

        pv.add(40);
        assert pv.validateBlackHeight() == 2;

        pv.add(50);
        pv.add(60);
        pv.add(70);
        assert pv.validateBlackHeight() == 2;
    }

    public void testBlackHeightAfterRemovals() {
        PV pv = new PV();

        pv.add(10);
        pv.add(20);
        pv.add(30);
        pv.add(40);
        assert pv.validateBlackHeight() == 2;

        pv.remove(40);
        assert pv.validateBlackHeight() == 2;

        pv.remove(30);
        pv.remove(20);
        assert pv.validateBlackHeight() == 1;

        pv.remove(10);
        assert pv.validateBlackHeight() == 0;
    }




    public void testBlackHeight() {
        PV pv = new PV();

        pv.add(10);
        pv.add(5);
        pv.add(14);
        pv.add(2);
        pv.add(15);
        pv.add(7);
        pv.add(12);
        pv.add(16);
        pv.add(6);

        assert pv.blackHeight(10) == 2;
        assert pv.blackHeight(5) == 2;
        assert pv.blackHeight(14) == 2;
        assert pv.blackHeight(2) == 1;
        assert pv.blackHeight(15) == 1;
        assert pv.blackHeight(7) == 1;
        assert pv.blackHeight(12) == 1;
        assert pv.blackHeight(16) == 1;
        assert pv.blackHeight(6) == 1;
    }
}
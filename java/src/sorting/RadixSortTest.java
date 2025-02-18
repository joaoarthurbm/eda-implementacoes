package sorting;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

public class RadixSortTest {

    RadixSort sorting = new RadixSort();
    int[] seq;

    @Test
    public void testSeqOrdenadaCres() {
        // mesma quantidade de dígitos
        seq = new int[]{300000, 300002, 300003, 300004, 300005, 300008};
        sorting.radixSort(seq);
        assertArrayEquals(new int[]{300000, 300002, 300003, 300004, 300005, 300008}, seq);

        // quantidade diferente de dígitos
        seq = new int[]{0, 220, 255, 430, 1000, 3750, 9800, 10127, 15000};
        sorting.radixSort(seq);
        assertArrayEquals(new int[]{0, 220, 255, 430, 1000, 3750, 9800, 10127, 15000}, seq);
    }

    @Test
    public void testSeqOrdenadaDecres() {
        // mesma quantidade de dígitos
        seq = new int[]{19009, 19008, 10807, 10706, 10005, 10004, 10003, 10002, 10001, 10000};
        sorting.radixSort(seq);
        assertArrayEquals(new int[]{10000, 10001, 10002, 10003, 10004, 10005, 10706, 10807, 19008, 19009}, seq);

        // quantidade diferente de dígitos
        seq = new int[]{15055, 12207, 9808, 87, 50, 2};
        sorting.radixSort(seq);
        assertArrayEquals(new int[]{2, 50, 87, 9808, 12207, 15055}, seq);
    }

    @Test
    public void testSeqComUmElemento() {
        seq = new int[]{1};
        sorting.radixSort(seq);
        assertArrayEquals(new int[]{1}, seq);
    }

    @Test
    public void testSeqComElementosIguais() {
        seq = new int[]{1111, 222221, 11111, 22222, 222221, 1111};
        sorting.radixSort(seq);
        assertArrayEquals(new int[]{1111, 1111, 11111, 22222, 222221, 222221}, seq);
    }

    @Test
    public void testSeqDesordenada() {
        seq = new int[]{737469, 732731, 735439, 413853, 454036, 159600, 363650, 885179, 0, 683885, 783765};
        sorting.radixSort(seq);
        assertArrayEquals(new int[]{0, 159600, 363650, 413853, 454036, 683885, 732731, 735439, 737469, 783765, 885179}, seq);

        seq = new int[]{551238, 109241, 684033, 100225, 906569, 159767, 539565, 704655};
        sorting.radixSort(seq);
        assertArrayEquals(new int[]{100225, 109241, 159767, 539565, 551238, 684033, 704655, 906569}, seq);

        seq = new int[]{743598, 887530, 570283, 30648, 730902, 190975, 419655, 283726};
        sorting.radixSort(seq);
        assertArrayEquals(new int[]{30648, 190975, 283726, 419655, 570283, 730902, 743598, 887530}, seq);

        seq = new int[]{230046, 165664, 332690, 130922, 987826, 812756, 281072, 128043, 719544, 924193};
        sorting.radixSort(seq);
        assertArrayEquals(new int[]{128043, 130922, 165664, 230046, 281072, 332690, 719544, 812756, 924193, 987826}, seq);

        seq = new int[]{633248, 370262, 63400, 75327, 837251, 485628, 291040};
        sorting.radixSort(seq);
        assertArrayEquals(new int[]{63400, 75327, 291040, 370262, 485628, 633248, 837251}, seq);

    }
}
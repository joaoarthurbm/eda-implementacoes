import java.util.Arrays;

public class RadixSort {

    public static void radixSort(int[] v) {
        int max = getMax(v);

        for (int i = 1; max / Math.pow(10, i - 1) > 0; i++) {
            countingSort(v, i);
        }
    }

    private static void countingSort(int[] v, int pos) {
        int[] a = new int[v.length];
        int[] c = new int[10];

        for (int i = 0; i < v.length; i++) {
            int digito = getDigit(v[i], pos);
            c[digito] += 1;
        }

        for (int i = 1; i < c.length; i++) {
            c[i] += c[i - 1];
        }

        for (int i = v.length - 1; i >= 0; i--) {
            int digito = getDigit(v[i], pos);
            a[c[digito] - 1] = v[i];
            c[digito]--;
        }

        for (int i = 0; i < v.length; i++) {
            v[i] = a[i];
        }
    }

    private static int getDigit(int N, int i) {
        return (int) (N / Math.pow(10, i - 1)) % 10;
    }

    private static int getMax(int[] v) {
        // Considerando que não vamos receber número inteiros < 0
        int max = -1;

        for (int i = 0; i < v.length; i++) {
            if (v[i] > max)
                max = v[i];
        }

        return max;
    }

}

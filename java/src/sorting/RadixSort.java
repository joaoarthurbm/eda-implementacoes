package sorting;

public class RadixSort {

    public radixSort(int[] seq) {
        int maxNum = getMax(seq);
        int qtyDigits = ("" + maxNum).length(); 

        for (int nthDigit = 1; nthDigit <= qtyDigits; nthDigit++) {
            sort(seq, nthDigit);  // aqui o counting sort é usado para ordenar a sequência pelo i-esimo dígito
        }
    }

    private int[] sort(int[] seq, int nthDigit) {
        int[] sortedSeq = new int[seq.length];
        int[] freq = new int[10];  // tamanho do array passa a ser o número de dígitos possíveis

        // frequência
        int digit;
        for (int i = 0; i < seq.length; i++) {
            digit = (int) (seq[i] % Math.pow(10, nthDigit));
            digit = (int) (digit / Math.pow(10, nthDigit - 1));

            freq[digit]++;
        }

        // cumulativa da frequência 
        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i-1];
        }

        for (int i = seq.length - 1; i >= 0; i--) {
            digit = (int) (seq[i] % Math.pow(10, nthDigit));
            digit = (int) (digit / Math.pow(10, nthDigit - 1));

            sortedSeq[freq[digit] - 1] = seq[i];
            freq[digit]--;
        }

        // copia os elementos ordenados pelo i-esimo digito para sequencia original a fim de mantê-la atualizada
        for (int i = 0; i < seq.length; i++){
            seq[i] = sortedSeq[i];
        }
    }

    private int getMax(int[] seq) {
        int max = seq[0];
        for (int num: seq) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }
}

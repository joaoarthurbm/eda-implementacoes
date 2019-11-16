package sorting;

import java.util.Arrays;

public class MedianOfThreePivotStrategy implements PivotStrategy {

    @Override
    public int pickPivotIndex(int[] values, int left, int right) {
        int first = values[left];
        int mid = values[(left + right) / 2];
        int last = values[right];
        
        int[] sorted = {first, mid, last};
        Arrays.sort(sorted);
        
        if (sorted[1] == values[left]) return left;
        else if (sorted[1] == values[(left + right)/2]) return (left+right)/2;
        else return right;
    }

}

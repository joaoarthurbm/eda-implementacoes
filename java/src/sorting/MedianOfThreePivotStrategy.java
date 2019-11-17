package sorting;

import java.util.Arrays;

public class MedianOfThreePivotStrategy implements PivotStrategy {

    @Override
    public int pickPivotIndex(int[] values, int left, int right) {
        int mid = (left + right) / 2;
        
        int[] sorted = {values[left], values[mid], values[right]};
        Arrays.sort(sorted);
        
        if (sorted[1] == values[left]) return left;
        else if (sorted[1] == values[mid]) return mid;
        else return right;
    }

}

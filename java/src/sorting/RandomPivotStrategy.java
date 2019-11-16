package sorting;

public class RandomPivotStrategy implements PivotStrategy {

    @Override
    public int pickPivotIndex(int[] values, int left, int right) {
        int range = right - left + 1;
        return (int)(Math.random() * range) + left;
    }

}

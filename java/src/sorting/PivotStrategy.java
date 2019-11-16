package sorting;

public interface PivotStrategy {

    /**
     * Return the index of the chosen pivot.
     * @param values
     * @param left
     * @param right
     * @return the index of the chosen pivot.
     */
    public int pickPivotIndex(int[] values, int left, int right);
    
}

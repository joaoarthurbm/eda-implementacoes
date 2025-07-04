package sorting;
public class QuickSort implements SortingAlgorithm {

    private PivotStrategy pivotPicker = new MedianOfThreePivotStrategy();

    public void sort(int[] v) {
        this.quickSort(v, 0, v.length - 1);
    }
	
    public void quickSort(int[] v, int left, int right) {
        if (left < right) {
	    int index_pivot = partition(v, left, right);
	    quickSort(v, left, index_pivot - 1);
	    quickSort(v, index_pivot + 1, right);
	}
    }

    public int hoarePartition(int[]v, int left, int right) {
        int pivot = v[left];
        int i = left + 1;
        int j = right;

        while (i <= j) {
            while (i <= j && v[i] <= pivot)
                i++;
            
	    while (i <= j && v[j] > pivot)
                j--;

             if (i < j) 
		swap(v, i, j);
        }	
	swap(v, left, j);
        return j;
    }

    private int partition(int[] v, int left, int right) {
	
        int indexPivot = this.pivotPicker.pickPivotIndex(v, left, right);
    
        // swap first and pivot
	swap(v, left, indexPivot);
	
	int pivot = v[left];
	int i = left;
		
	for (int j = i + 1; j <= right; j++) {
	    if (v[j] <= pivot) {
		i+=1;
		swap(v, i, j);
	    }
	}
	
	swap(v, left, i);
	
	return i;
		
    }

	private void swap(int[] v, int i, int j) {
		int aux = v[i];
		v[i] = v[j];
		v[j] = aux;
	}
}

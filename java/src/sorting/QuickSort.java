package sorting;
public class QuickSort implements SortingAlgorithm {

	public void quickSort(int[] v, int left, int right) {
		if (left < right) {
			int index_pivot = particiona(v, left, right);
			quickSort(v, left, index_pivot - 1);
			quickSort(v, index_pivot + 1, right);	
		}
	}

	private int particiona(int[] v, int left, int right) {
		
		int pivot = v[left];
		int i = left;
		
		for (int j = i + 1; j <= right; j++) {
			if (v[j] < pivot) {
				i+=1;
				int aux = v[i];
				v[i] = v[j];
				v[j] = aux;
				
			}
		}
		
		int aux = v[left];
		v[left] = v[i];
		v[i] = aux;
		
		return i;
		
	}

    @Override
    public void sort(int[] v) {
        this.quickSort(v, 0, v.length - 1);
    }

}

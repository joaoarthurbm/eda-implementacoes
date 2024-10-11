package sorting;

public class MergeSort implements SortingAlgorithm {
	
	public void mergeSort(int[] v, int left, int right) {
		
		if (left < right) {
			
			int middle = (left + right) / 2;
			mergeSort(v, left, middle);
			mergeSort(v, middle + 1, right);
	
			merge(v, left, middle, right);
		}
		
	}

	public void merge(int[] v, int left, int middle, int right) {
		
		// transfere os elementos entre left e right para o array auxiliar.
		int[] helper = new int[v.length];
		for (int i = left; i <= right; i++) {
			helper[i] = v[i];
		}
		
		
		int i = left;
		int j = middle + 1;
		int k = left;
		
		while (i <= middle && j <= right) {
			
			if (helper[i] < helper[j]) {
				v[k] = helper[i];
				i++;
			} else {
				v[k] = helper[j];
				j++;
			}
			k++;	
			
		}
		
		// se a metade inicial não foi toda consumida, faz o append.
		while (i <= middle) {
			v[k] = helper[i];
			i++;
			k++;
		}

		// Não precisamos nos preocupar se a metade final foi
		// toda consumida, já que, se esse foi o caso, ela já está
		// no array final.

	}

	@Override
	public void sort(int[] v) {
		mergeSort(v, 0, v.length - 1);
	}

}

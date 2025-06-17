package sorting;

public class MergeSort implements SortingAlgorithm {
	
	public void mergeSort(int[] v, int left, int right) {
		
		if (left < right) {
			
			int middle = (left + right) / 2;
			mergeSort(v, left, middle);
			mergeSort(v, middle + 1, right);
	
			merge(v, left, right);
		}
		
	}
	
	public void merge(int[] v, int left, int right) {
		
		// transfere os elementos entre left e right para o array auxiliar.
		int rightHelper = right - left;
		int[] helper = new int[rightHelper + 1];
		for (int i = 0; i <= rightHelper; i++)
			helper[i] = v[left + i];
		
		int middleHelper = rightHelper / 2;

		int i = 0;
		int j = middleHelper + 1;
		int k = left;
		
		while (i <= middleHelper && j <= rightHelper) {
			
			if (helper[i] <= helper[j])
			  v[k++] = helper[i++];
			else
				v[k++] = helper[j++];
		}
		
		// se a metade inicial não foi toda consumida, faz o append.
		while (i <= middleHelper)
			v[k++] = helper[i++];
		
		// Não precisamos nos preocupar se a metade final foi
		// toda consumida, já que, se esse foi o caso, ela já está
		// no array final.

	}

	@Override
	public void sort(int[] v) {
		mergeSort(v, 0, v.length - 1);
	}

}


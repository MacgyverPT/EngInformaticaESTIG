public class BubbleSort {

	public static void sort(int[] A){
        int aux = 0;
		
		for(int i = 0; i < A.length; i++){
			for(int j = 0; j < A.length - 1; j++){
				if(A[j] > A[j + 1]){
					aux = A[j];
					A[j] = A[j+1];
					A[j+1] = aux;
				}
			}
		}
		
    }
}
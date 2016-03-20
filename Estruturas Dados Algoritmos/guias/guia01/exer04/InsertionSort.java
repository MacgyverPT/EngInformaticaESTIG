public class InsertionSort{
    
	public static void sort(int[] A){
        for (int j = 0; j < A.length; j++){
            int key = A[j];
            int i = j - 1;
            
            while(i > -1 && A[i] > key){
                A[i + 1] = A[i];
                i = i - 1;
            }
            A[i + 1] = key;
        }
    }
	
}

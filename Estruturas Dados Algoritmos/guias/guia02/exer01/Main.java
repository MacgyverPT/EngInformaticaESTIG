import java.util.Random;


public class Main {
	

	public static void main(String[] args) {
		Random randomGenerator = new Random();
		int maxNumbers = 15;
		//int A[] = new int[maxNumbers];
		int A[] = {38, 27, 43, 3, 9, 82, 10};
		
		//adicionar os random no array "A"
		/*for (int i = 0; i < maxNumbers; i++){
			A[i] = randomGenerator.nextInt(100);
		}*/
		
		
		//show all elements (array)
		/*for(int x: A){
            System.out.println(" " + x);
        }*/
		

		MergeSort ms = new MergeSort();
		ms.run(A, 10, 2); //ms.run(A, p, r);
		
		
		System.out.print("\nFinish");
		
	}
	
}

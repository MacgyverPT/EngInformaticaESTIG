public class Main {

	public static void main(String[] args) {
		
		int A[] = {5, 2, 4, 6, 1, 3};

        
        //Fibonacci Recursivo
        FibonacciIterativo fi = new FibonacciIterativo();
        
        for (int i = 0; i < 10; i++){
            System.out.print("#" + (i+1) + ": " + fi.fibo(i) + "\n");
        }
        
    }
	
}

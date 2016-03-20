public class Main {

	public static void main(String[] args) {
		
		int A[] = {5, 2, 4, 6, 1, 3};

        
        FibonacciRecursivo fr = new FibonacciRecursivo();
        
        // teste do programa. Imprime os 10 primeiros termos
        for (int i = 0; i < 10; i++){
            System.out.print("#" + (i+1) + ": " + fr.fibo(i) + "\n");
        }
        
    }
	
}

public class Main {

	public static void main(String[] args) {
		
		int A[] = {5, 2, 4, 6, 1, 3, 15, 10, 22, 14, 7};
		
		System.out.printf("\nArray Original: ");
        for(int x: A){
            System.out.print(" " + x + ", ");
        }
        
        //Bubble Sort
        BubbleSort bs = new BubbleSort();
        
        long tempoInicial = System.currentTimeMillis();
        bs.sort(A);
        long tempoFinal = System.currentTimeMillis();
        
        
        
        System.out.printf("\nArray Ordenado: ");
        for(int x: A){
            System.out.print(" " + x + ", ");
        }
        
        //saber o tempo em segundos (dividir por 1000)
        System.out.printf("\n\nTempo utilizado: %.3f\n", (tempoFinal - tempoInicial) / 1000d);
    }
	
}

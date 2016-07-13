#include <msp430.h> 

#define LED_GREEN 	BIT6
#define LED_RED 	BIT0
#define BUTTON 		BIT3

void main(void) {
	WDTCTL = WDTPW | WDTHOLD;	// Stop watchdog timer


	//Passo 1 - configurar pinos

	P1OUT = LED_GREEN;	// P1.6 - estabelece valor de saída a 1
	P1DIR = LED_GREEN; 	// P1.6 - configurado como saída digital



	//Passo 2 - configura comparador (Comparator_A+)
	// tensão de referência é aplica ao terminal -; usada a refeência da tensão do díodo; ativa comparador
	CACTL1 = CARSEL | CAREF_3 | CAON;
	// seleciona CA2 (pino P1.2) como entrada +
	CACTL2 = P2CA4 | P2CA0;  // pino P1.2 é comparado com a referência

	while (1)
	{
		if(CACTL2 & CAOUT) // testa saída do comparador
			P1OUT |= LED_GREEN; // acende LED verde
		else
			P1OUT &= ~LED_GREEN; // apaga LED verde
	}
}


#include <msp430.h> 

#define LED_GREEN 	BIT6
#define LED_RED 	BIT0
#define BUTTON 		BIT3

void main(void) {
	WDTCTL = WDTPW | WDTHOLD;	// Stop watchdog timer


	//Passo 1 - configurar pinos

	P1OUT = LED_GREEN;	// P1.6 - estabelece valor de sa�da a 1
	P1DIR = LED_GREEN; 	// P1.6 - configurado como sa�da digital



	//Passo 2 - configura comparador (Comparator_A+)
	// tens�o de refer�ncia � aplica ao terminal -; usada a refe�ncia da tens�o do d�odo; ativa comparador
	CACTL1 = CARSEL | CAREF_3 | CAON;
	// seleciona CA2 (pino P1.2) como entrada +
	CACTL2 = P2CA4 | P2CA0;  // pino P1.2 � comparado com a refer�ncia

	while (1)
	{
		if(CACTL2 & CAOUT) // testa sa�da do comparador
			P1OUT |= LED_GREEN; // acende LED verde
		else
			P1OUT &= ~LED_GREEN; // apaga LED verde
	}
}


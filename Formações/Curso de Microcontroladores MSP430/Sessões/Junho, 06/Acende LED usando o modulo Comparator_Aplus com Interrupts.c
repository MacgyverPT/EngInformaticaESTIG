#include <msp430.h> 

#define LED_GREEN 	BIT6
#define LED_RED 	BIT0
#define BUTTON 		BIT3

void main(void) {
	WDTCTL = WDTPW | WDTHOLD;	// Stop watchdog timer


	//Passo 1 - configurar pinos

	P1OUT &= ~LED_GREEN;	// P1.6 - estabelece valor de saída a 0
	P1DIR = LED_GREEN; 	// P1.6 - configurado como saída digital



	//Passo 2 - configura comparador (Comparator_A+)
	CACTL1 = CARSEL | CAREF_3 | CAON | CAIE;
	CACTL2 = P2CA4 | P2CA0;

	_enable_interrupts();

	while (1)
	{
		_low_power_mode_4();
	}
}

#pragma vector = COMPARATORA_VECTOR;
__interrupt void ComparatorA_ISR()
{
	P1OUT ^= LED_GREEN; // acende LED verde
	CACTL1 ^= CAIES;
}

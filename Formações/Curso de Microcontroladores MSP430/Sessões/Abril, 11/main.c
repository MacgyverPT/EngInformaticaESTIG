#include <msp430.h> 

void main(void) {
	WDTCTL = WDTPW | WDTHOLD;	// Stop watchdog timer

	P1OUT = BIT6;	// P1.6 - estabelece valor de sa�da a 1
	P1DIR = BIT6; 	// P1.6 - configurado como sa�da digital
	P1DIR &= ~BIT3; // P1.3 - configurado como entrada digital
	P1REN |= BIT3;	// ativa resist�ncia de entrada de P1.3
	P1OUT |= BIT3;	// resist�ncia entrada de P1.3 como pullup

	while (1) {
		if ((P1IN & BIT3) == 0) // bot�o carregado
		{
			P1OUT |= BIT6; 		// acende LED
		}
		P1OUT &= ~BIT6; 		// apaga LED
	}
}

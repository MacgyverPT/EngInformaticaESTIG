#include <msp430.h> 

#define LED_GREEN 	BIT6
#define LED_RED 	BIT0
#define BUTTON 		BIT3

void main(void) {
	WDTCTL = WDTPW | WDTHOLD;	// Stop watchdog timer


	//Passo 1 - configurar pinos

	P1OUT = LED_GREEN;	// P1.6 - estabelece valor de saída a 1
	P1DIR = LED_GREEN; 	// P1.6 - configurado como saída digital
	P1DIR &= ~BUTTON; 	// P1.3 - configurado como entrada digital
	P1REN |= BUTTON;	// ativa resistência de entrada de P1.3
	P1OUT |= BUTTON;	// resistência entrada de P1.3 como pullup

	P1IE = BUTTON;		// habilita interrupção no pino P1.3 (botão)
	P1IES |= BUTTON; 	// interrupção na transição 1->0
	P1IFG &= ~BUTTON;	// certifica que indicador de int. está limpo




	while (1)
	{
		_low_power_mode_4();  // desliga relógios
	}
}


#pragma vector = PORT1_VECTOR
__interrupt void PORT1_ISR()
{
	P1OUT ^= LED_GREEN;	//alterna LED
	P1IFG &= ~BUTTON;	//limpa o indicador de interrupt do botão em P1.3
}

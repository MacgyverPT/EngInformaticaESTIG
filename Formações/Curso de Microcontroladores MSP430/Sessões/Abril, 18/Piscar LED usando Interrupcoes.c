#include <msp430.h> 

#define LED_GREEN 	BIT6
#define LED_RED 	BIT0
#define BUTTON 		BIT3

void main(void) {
	WDTCTL = WDTPW | WDTHOLD;	// Stop watchdog timer


	//Passo 1 - configurar pinos

	P1OUT = LED_GREEN;	// P1.6 - estabelece valor de sa�da a 1
	P1DIR = LED_GREEN; 	// P1.6 - configurado como sa�da digital
	P1DIR &= ~BUTTON; 	// P1.3 - configurado como entrada digital
	P1REN |= BUTTON;	// ativa resist�ncia de entrada de P1.3
	P1OUT |= BUTTON;	// resist�ncia entrada de P1.3 como pullup

	P1IE = BUTTON;		// habilita interrup��o no pino P1.3 (bot�o)
	P1IES |= BUTTON; 	// interrup��o na transi��o 1->0
	P1IFG &= ~BUTTON;	// certifica que indicador de int. est� limpo




	while (1)
	{
		_low_power_mode_4();  // desliga rel�gios
	}
}


#pragma vector = PORT1_VECTOR
__interrupt void PORT1_ISR()
{
	P1OUT ^= LED_GREEN;	//alterna LED
	P1IFG &= ~BUTTON;	//limpa o indicador de interrupt do bot�o em P1.3
}

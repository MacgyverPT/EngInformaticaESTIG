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


	//Passo 2 - configurar relógios

	//configurar DCO a 8 MHz
	if( CALDCO_8MHZ == 0xFF || CALBC1_8MHZ == 0xFF)
	{
		while(1);  // não avança se as constantes de calibração não estiverem definidas em memória
	}
	DCOCTL = CALDCO_8MHZ;
	BCSCTL1 = CALBC1_8MHZ;

	//configuração ACLK: alimentado pelo VLO
	BCSCTL3 |= LFXT1S_2;	// alimentado pelo VLO
	BCSCTL1 |= DIVA_0; 		// divide frequência por 1

	//configuração MCLK: DCO e DIV=1 e SMCLK: DCO e DIV=8
	BCSCTL2 = SELM_1 | DIVM_0 | DIVS_3;

	//Passo 3 - configurar Timer_A
	TA0CTL = TASSEL_1 | ID_0 | MC_2 | TACLR | TAIE;


	_enable_interrupts(); //habilita interrupções não mascaráveis

	while (1) {
		_low_power_mode_0();

	}
}

#pragma vector = TIMER0_A1_VECTOR; // IV para T0A e CCR1 e CCR2
__interrupt void timer0_A1_ISR()
{
//	P1OUT ^= LED_GREEN;

	switch( TA0IV )
	 {
	   case TA0IV_TACCR1: break;		// 2 CCR1 not used
	   case TA0IV_TACCR2: break;		// 8 CCR2 not used
	   case TA0IV_TAIFG:                // 10 TAR overflow
		   P1OUT ^= LED_GREEN;
		   break;
	 }

}



#pragma vector = PORT1_VECTOR
__interrupt void PORT1_ISR()
{
	P1OUT ^= LED_GREEN;	//alterna LED
	P1IFG &= ~BUTTON;	//limpa o indicador de interrupt do botão em P1.3
}

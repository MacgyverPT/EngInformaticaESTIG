#include <msp430.h> 

#define TICKS	100 //contagens/s

/*
 * main.c
 */
void main(void) {
    WDTCTL = WDTPW | WDTHOLD;	// Stop watchdog timer
	
    // 1 - Configurar pinos

   P1OUT &= ~(BIT6 | BIT0); // coloca
   P1DIR = BIT6 | BIT2 | BIT0;	// configura P1.0 e P1.6 como sa�das (LEDs)
   P1SEL = BIT2;  	   	   	   	// P1.2 OUT1 do timer TA0

   // 2 - Configurar rel�gios
   //configurar DCO a 1 MHz
   	if( CALDCO_1MHZ == 0xFF || CALBC1_1MHZ == 0xFF)
   	{
   		while(1);  // n�o avan�a se as constantes de calibra��o n�o estiverem definidas em mem�ria
   	}
   	DCOCTL = CALDCO_1MHZ;
   	BCSCTL1 = CALBC1_1MHZ;

   	//configura��o ACLK: alimentado pelo VLO
	BCSCTL3 |= LFXT1S_2;	// alimentado pelo VLO
	BCSCTL1 |= DIVA_0; 		// divide frequ�ncia por 1

	//configura��o MCLK: DCO e DIVM=1 e SMCLK: DCO e DIVS=1 -> SMCLK=1MHz
	BCSCTL2 = SELM_2 | DIVM_0 | DIVS_0;


	// 2 - Configurar Timer_A
	//source: SMCLK, divisor:2  modo contagem: up at� TACCR0
	TA0CTL = TASSEL_2 | ID_1 | MC_1 |TACLR; // 62500 contagens/s

	// configurar sub-m�dulo CCR0:
	TA0CCR0 = TICKS;


	// configurar sub-m�dulo CCR1:
	TA0CCR1 = 5; //1s com SMCLK = 100kHz
	//compare mode,
	TA0CCTL1 = OUTMOD_7; // reset/set
	//PWM pin will be toggled without needing an interrupt.

	// Para piscar o LED remover o jumper de um dos LEDs e conectar o LED ao pino P1.2



	_low_power_mode_1();

}


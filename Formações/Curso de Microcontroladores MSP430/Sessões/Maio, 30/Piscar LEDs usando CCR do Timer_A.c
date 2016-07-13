#include <msp430.h> 

#define TICKS1s		31250-1 //1s a 62500 contagens/s
#define TICKS05s 	15625-1 //0.5 s a 62500 contagens/s
#define TICKS025s 	TICKS05s >> 1 -1 //0.5 s a 62500 contagens/s
/*
 * main.c
 */
void main(void) {
    WDTCTL = WDTPW | WDTHOLD;	// Stop watchdog timer
	
    // 1 - Configurar pinos

   P1OUT &= ~(BIT6 | BIT0); //coloca
   P1DIR = BIT6 | BIT0;	//configura P1.0 e P1.6 como saídas (LEDs)


   // 2 - Configurar relógios
   //configurar DCO a 1 MHz
   	if( CALDCO_1MHZ == 0xFF || CALBC1_1MHZ == 0xFF)
   	{
   		while(1);  // não avança se as constantes de calibração não estiverem definidas em memória
   	}
   	DCOCTL = CALDCO_1MHZ;
   	BCSCTL1 = CALBC1_1MHZ;

   	//configuração ACLK: alimentado pelo VLO
	BCSCTL3 |= LFXT1S_2;	// alimentado pelo VLO
	BCSCTL1 |= DIVA_0; 		// divide frequência por 1

	//configuração MCLK: DCO e DIVM=1 e SMCLK: DCO e DIVS=8 -> SMCLK=125kHz
	BCSCTL2 = SELM_2 | DIVM_0 | ~SELS | DIVS_3;


	// 2 - Configurar Timer_A

	//source: SMCLK, divisor:2  modo contagem: contínuo até 0xFFFF
	TA0CTL = TASSEL_2 | ID_1 | MC_2 |TACLR; // 62500 contagens/s

	// configurar registo CC0
	//TA0CCR0 = 0XFFFF;

	// configurar registo CC1
	TA0CCR1 = TICKS1s; //1s com SMCLK = 125kHz
	//modo comparação (compare mode) por defeito
	TA0CCTL1 = CCIE; // habilita interrupção do CC1

	// configurar registo CC2
	TA0CCR2 = TICKS05s;	//0.5s com SMCLK = 125kHz
	TA0CCTL2 = CCIE; // habilita interrupção do CC2


	__enable_interrupt();
	_low_power_mode_1();

}


#pragma vector = TIMER0_A1_VECTOR;
__interrupt void T0A1_ISR( void )
{
	switch ( TA0IV )
	{
		case TA0IV_TACCR1:
			//comuta LED verde
			P1OUT ^= BIT6;
			TA0CCR1 += TICKS1s;
			break;
		case TA0IV_TACCR2:
			//comuta LED vermelho
			P1OUT ^= BIT0;
			TA0CCR2 += TICKS025s;
			break;
		case TA0IV_TAIFG:
			//do nothing;
			break;
	}
}

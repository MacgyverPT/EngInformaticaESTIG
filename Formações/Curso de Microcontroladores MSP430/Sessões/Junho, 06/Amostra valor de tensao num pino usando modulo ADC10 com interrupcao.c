#include <msp430.h> 

#define LED_GREEN 	BIT6
#define LED_RED 	BIT0
#define BUTTON 		BIT3

int sample = 0;

void main(void) {
	WDTCTL = WDTPW | WDTHOLD;	// Stop watchdog timer


	//Passo 1 - configurar pinos

	P1OUT = LED_GREEN;	// P1.6 - estabelece valor de sa�da a 1
	P1DIR = LED_GREEN; 	// P1.6 - configurado como sa�da digital




	//Passo 2 - configura ADC10
	// SREF: Vcc e Vss; SHT: 8 ciclos; ADC10 on
	ADC10CTL0 = SREF_0 | ADC10SHT_1 | ADC10ON | ADC10IE;

	// canal A7 (pino P1.7); trigger SHS com bit ADC10SC; divisor rel�gio =1; usa rel�gio interno do ADC;
	// convers�o �nica de um canal �nico
	ADC10CTL1 = INCH_7 | SHS_0 | ADC10DIV_0 | ADC10SSEL_0 | CONSEQ_0;

	// habilita entrada anal�giva no pino P1.7
	ADC10AE0 |= BIT7;

	// habilita convers�o
	ADC10CTL0 |= ENC;


	_enable_interrupts();

	while (1)
	{
		ADC10CTL0 |= ADC10SC;  //come�a amostragem por software
	}
}

#pragma vector = ADC10_VECTOR;
__interrupt void ADC10_ISR( void )
{
	sample = ADC10MEM;

}




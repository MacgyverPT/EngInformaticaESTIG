#include <msp430.h> 

/*
 * main.c
 */
void main(void) {
    WDTCTL = WDTPW | WDTHOLD;	// Stop watchdog timer
	

    //Configure I/O
    P1OUT = 0x00; // clear output buffer
    P1DIR = 0xFF;  // all pins as outputs
    P1DIR &= ~(BIT4 | BIT3); // pin 3 and 4 as inputs

    	// configure input pins
    P1REN |= (BIT4 | BIT3); //enable pull-up/pull-down resistor
    P1OUT |= (BIT4 | BIT3); // configure input resistors as pull-up's

    //Configure interrups
    P1IE |= (BIT4 | BIT3); // enable interrupts of pins 3 and 4
    P1IES |= BIT3;	// interrup: 1->0
    P1IES &= ~BIT4; // interrupt: 0->1
    P1IFG &= ~(BIT4 | BIT3); // clear any interrupt flag

    _enable_interrupts(); //


    while(1)
    {
    	_low_power_mode_4();
    }
}

#pragma vector = PORT1_VECTOR
__interrupt void PORT1_ISR()
{
	//P1OUT ^= BIT6;
	 //P1IFG &= ~(BIT4 | BIT3);


	if((P1IFG & BIT3) == BIT3) //test interrupt flag from pin 3
	{
		P1OUT |= BIT6;
		P1IFG &= ~BIT3;
	}

	if((P1IFG & BIT4) == BIT4) //test interrupt flag from pin 4
	{
		P1OUT &= ~BIT6;
		P1IFG &= ~BIT4;
	}

}

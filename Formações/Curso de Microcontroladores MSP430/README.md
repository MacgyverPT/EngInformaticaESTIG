Código das Sessões


Sessão: 11 abril 2016
> Piscar LED quando se carrega em botão

Sessão: 18 abril 2016
> Piscar LED usando interrupções do porto P1

Sessão: 2 maio 2016
> Acende o LED usando a interrupção na transição (1->0) e apaga o LED na transição (0->1).

Sessão: 23 maio 2016
> Pisca LED usando o bloco temporizador do Timer_A e a interrupção TA0IFG.

Sessão: 30 maio 2016
> Pisca LEDs de com frequência independente usando  os blocos comparadores CCR1 e CCR2 do Timer_A e as interrupções TA0IV_TACCRx.
> Pisca LED usando um sinal de output no pino P1.2 de PWM gerado pelo CCR1 do Timer0_A. O sinal de PWM é gerado sem intervenção do CPU e, por isso, não é necessário recorrer a interrupções. (É necessário ligar o sinal PWM a um dos LEDs: retirar o jumper e conectar o pino Out2 em P1.2 ao LED usando um fio de ligação.)

Sessão: 6 junho 2016
> Quando a tensão na entrada CA2 (pino P1.2) é maior que a tensão de referência o LED acende. Quando a tensão em CA2 é menor que a tensão de referência o LED apaga.
> Quando a tensão na entrada CA2 (pino P1.2) é maior que a tensão de referência o LED acende. Quando a tensão em CA2 é menor que a tensão de referência o LED apaga. Em vez de estar sempre a realizar a comparação, quando o tensão em CA2 ultrapassa a tensão de referência é gerado uma interrupção.
> O módulo ADC10 é configurado para a amostrar a tensão do pino P1.7 O valor da tensão é traduzido para um úmero inteiro entre 0 e 2^10-1 (1023). O valor da conversão é guardado na variável sample cujo valor pode observado na janela Expressions em modo debug.
> O módulo ADC10 é configurado para a amostrar a tensão do pino P1.7 Quando a conversão termina é gerada uma interrupção e só então o valor é copiado para a variável global  sample.
C�digo das Sess�es


Sess�o: 11 abril 2016
> Piscar LED quando se carrega em bot�o

Sess�o: 18 abril 2016
> Piscar LED usando interrup��es do porto P1

Sess�o: 2 maio 2016
> Acende o LED usando a interrup��o na transi��o (1->0) e apaga o LED na transi��o (0->1).

Sess�o: 23 maio 2016
> Pisca LED usando o bloco temporizador do Timer_A e a interrup��o TA0IFG.

Sess�o: 30 maio 2016
> Pisca LEDs de com frequ�ncia independente usando  os blocos comparadores CCR1 e CCR2 do Timer_A e as interrup��es TA0IV_TACCRx.
> Pisca LED usando um sinal de output no pino P1.2 de PWM gerado pelo CCR1 do Timer0_A. O sinal de PWM � gerado sem interven��o do CPU e, por isso, n�o � necess�rio recorrer a interrup��es. (� necess�rio ligar o sinal PWM a um dos LEDs: retirar o jumper e conectar o pino Out2 em P1.2 ao LED usando um fio de liga��o.)

Sess�o: 6 junho 2016
> Quando a tens�o na entrada CA2 (pino P1.2) � maior que a tens�o de refer�ncia o LED acende. Quando a tens�o em CA2 � menor que a tens�o de refer�ncia o LED apaga.
> Quando a tens�o na entrada CA2 (pino P1.2) � maior que a tens�o de refer�ncia o LED acende. Quando a tens�o em CA2 � menor que a tens�o de refer�ncia o LED apaga. Em vez de estar sempre a realizar a compara��o, quando o tens�o em CA2 ultrapassa a tens�o de refer�ncia � gerado uma interrup��o.
> O m�dulo ADC10 � configurado para a amostrar a tens�o do pino P1.7 O valor da tens�o � traduzido para um �mero inteiro entre 0 e 2^10-1 (1023). O valor da convers�o � guardado na vari�vel sample cujo valor pode observado na janela Expressions em modo debug.
> O m�dulo ADC10 � configurado para a amostrar a tens�o do pino P1.7 Quando a convers�o termina � gerada uma interrup��o e s� ent�o o valor � copiado para a vari�vel global  sample.
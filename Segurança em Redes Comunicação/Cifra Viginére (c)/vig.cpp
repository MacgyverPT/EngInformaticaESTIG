/*
Disciplina: Segurança em Redes de Computadores
Docente: Prof. Rui Silva

Trabalho consiste na criação da cifra de Viginère.

Autores:
- Ana Pontes: 5950
- Miguel Rosa: 6219

ESTiG - Outubro de 2015
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAXCHAR 9999
 
 //função para colocar as letras em maiusculas
void upper_case(char *src)
{
	while (*src != '\0'){ //enquanto não chegar ao final da string
		if ( islower(*src) ){ //se a letra for minuscula
			*src &= ~0x20; 
		}
		src++;
	}
}
 
 //funcao para cifrar ou decifrar
char* cifraVig(const char *src, char *key, int cifraDecifra)
{
	int i, klen, slen;
	char *dest;

	dest = strdup(src); //duplica a string
	upper_case(dest); //coloca a mensagem em letra maiuscula
	upper_case(key); //coloca a chave em letra maiuscula
 
        
	for (i = 0, slen = 0; dest[slen] != '\0'; slen++)
		if ( isupper(dest[slen]) ){//o "isupper" verifica se o char é letra maiuscula
			dest[i++] = dest[slen]; //se não for letra maiuscula, coloca-o
		} 
		
	dest[slen = i] = '\0'; //termina a string caso ainda haja "espaço" no array

	klen = strlen(key); //guarda o tamanho da chave
	for (i = 0; i < slen; i++) {
		if ( !isupper(dest[i]) ){ //o "isupper" verifica se o char é letra maiuscula
			continue; 
		}
			
		dest[i] = 'A' + (cifraDecifra /*verifica a opcao tomada no main*/
				/*0 - decifrar*/? dest[i] - 'A' + key[i % klen] - 'A'
				/*1 - cifrar*/: dest[i] - key[i % klen] + 26) % 26;
	}

	return dest; //retorna o resultado se a mensagem foi cifrada ou decifrada
}
 
int main(int argc, char* argv[])
{
    char *codificar, *decifrar;
    char input[2];
    char mensagemArray[MAXCHAR];
    char key[MAXCHAR];
    char criptoArray[MAXCHAR];
    FILE *fileMensagem;
    FILE *fileChave;
    FILE *fileViginere;
    int m = 0, c = 0, i = 0, d = 0, slen = 0, klen = 0, clen = 0;



    //////////////
    //  CIFRAR  //
    /////////////
    if( (strcmp(argv[1],"c") == 0) || (strcmp(argv[1],"C") == 0) ){ //compara se o argumento inserido é "c" ou "C"
        
		// LER A MENSAGEM
        fileMensagem = fopen("mensagem.txt", "r"); //abre o ficheiro em modo leitura
        //enquanto nao chegar ao final do ficheiro, lê o ficheiro char a char
        while ( (m = fgetc(fileMensagem)) != EOF) { 
            mensagemArray[slen] = m; //copia para o array mensagem a mensagem vinda do ficheiro txt
            slen++;                  //saber o tamanho maximo da mensagem. Vai contando o num de char
        }
        fclose(fileMensagem);


        //LER A CHAVE
        fileChave = fopen("chave.txt", "r"); //abre o ficheiro em modo leitura
        //enquanto nao chegar ao final do ficheiro, lê o ficheiro char a char
        while( (c = fgetc(fileChave)) != EOF ){
            key[klen] = c; //copia para o array da chave a posição do C, ou seja, a posicao da letra que esta a ler 
            klen++;
        }
        fclose(fileChave);

        codificar = cifraVig(mensagemArray, key, 1); //chama a funcao "cifraVig" e cifra a mensagem usando a chave
        printf("\n\n OUTPUT \n");
        printf("        Mensagem: %s\n", mensagemArray);
        printf("           Chave: %s\n", key);
        printf("Mensagem cifrada: %s\n", codificar);
        printf("\n\n");


        //salva a mensagem num ficheiro .vig
        fileViginere = fopen("criptograma.vig", "w"); //abre o ficheiro em modo escrita
        if (fileViginere != NULL){
            fprintf(fileViginere, "%s\n", codificar); //guarda no ficheiro o resultado da cifra
        }

        fclose(fileViginere);
    }



    //////////////
    // DECIFRAR //
    /////////////
    if( (strcmp(argv[1],"d") == 0) || (strcmp(argv[1],"D") == 0) ){ //compara se o argumento inserido é "d" ou "D"

        //ler a chave
        fileChave = fopen("chave.txt", "r"); //abre o ficheiro em modo leitura
        //enquanto nao chegar ao final do ficheiro, lê o ficheiro char a char
        while( (c = fgetc(fileChave)) != EOF ){
            key[klen] = c; //copia para o array da chave a posição do C, ou seja, a posicao da letra que esta a ler 
            klen++;
        }
        fclose(fileChave);

        //ler o ficheiro do criptograma
        fileViginere = fopen("criptograma.vig", "r"); //abre o ficheiro em modo leitura
        //enquanto nao chegar ao final do ficheiro, lê o ficheiro char a char
        while( (d = fgetc(fileViginere)) != EOF ){
            criptoArray[clen] = d; //copia para o array do criptograma a posição do d, ou seja, a posicao da letra que esta a ler 
            clen++;
        }
        fclose(fileViginere);

        printf("     Criptograma: %s\n", criptoArray); //linha de teste APAGAR


        decifrar = cifraVig(codificar, key, 0);

        printf("\n\n OUTPUT \n");
        printf("     Criptograma: %s\n", criptoArray);
        printf("           Chave: %s\n", key);
        printf("        Mensagem: %s\n", decifrar);


        //salva a mensagem num ficheiro .txt
        fileMensagem = fopen("mensagem.txt", "w"); //abre o ficheiro em modo escrita
        if (fileMensagem != NULL){
            fprintf(fileMensagem, "%s\n", decifrar); //guarda no ficheiro o resultado da decifra
        }

        fclose(fileMensagem);
    }


    
    return 0;
}
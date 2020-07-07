#include <stdlib.h>
#include <stdio.h>


//Prototipos
int sgteCompLex();
void procesaP();
void procesaS();
void procesaR();
void procesaA();
void procesaB();
void parea(char parear);
void error();


int sig; //Almacena el siguiente caracter.

int main(){
	sig = sgteCompLex();
	procesaP();
}


int sgteCompLex() {
	char c = getchar();
	while (c==' ' || c=='\t' || c=='\n') c = getchar(); // saltar blancos ...
	switch (c) {
		case 'a': case 'b': case ';': case EOF: return c;
		default: {
			printf ("Error léxico\n");
			exit(1);
		}
	}
}

void procesaP(){
	switch(sig){
		case 'a': case 'b': case ';':
			procesaS();
			parea(';');
			procesaP();
			break;
		case EOF:
			break;
		default: 
			error();
	}
}

void procesaS(){
        switch(sig){
                case 'a': case 'b':
                	procesaR();
			break;
                case ';':
                	break;
                default: 
			error();
        }
}

void procesaR(){
        switch(sig){
                case 'a':
			parea('a');
                	procesaB();
                	procesaS();
			break;
		case 'b':
			parea('b');
			procesaA();
			procesaS();
			break;	
          	default :
			error();
        }
}

void procesaA(){
        switch(sig){
                case 'a':
                	parea('a');
			break;
		case 'b':
			parea('b');
			procesaA();
			procesaA();
			break;
                default: 
			error();
        }
}

void procesaB(){
        switch(sig){
                case 'a':
	       		parea('a');
			procesaB();
			procesaB();
			break;
		case 'b':
			parea('b');
			break;
                default: 
			error();
			
                        
        }
}

void parea(char parear){
	if(sig != parear){
		error();
	}else{
	sig = sgteCompLex();
	}
}


void error(){
	printf("La expresión no cumple la gramática\n");
        exit(1);
}

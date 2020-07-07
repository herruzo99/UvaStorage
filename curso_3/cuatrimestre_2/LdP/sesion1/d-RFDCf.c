#include <stdio.h>
/* RECONOCEDOR SIMPLE para a(a|b)*
   DETERMINISTA COMPLETO
   Alfabeto de entrada: a, b. Si hay símbolos extraños, el programa muestra un mensaje
   La entrada termina con fin de línea (\n)
*/

int siguiente (int q, char c) {
	// PRE: c pertenece al alfabeto de entrada
	//	q es un número de estado válido 
	//	(es decir: 1<=q<=3 'a' <= c <='b') 
	switch (q) {
		case 1: 
			if 	(c=='a') return 2;
			else if (c=='b') return 3;
		break;
		case 2: 
			if 	(c=='a') return 2;
			else if (c=='b') return 2;
		break;
		case 3: 
			if 	(c=='a') return 3;
			else if (c=='b') return 3;
		break;
	}
	return -1;
}

int final (int q) {
	if (q==2) return 1==1; 
	else 	  return 1==0;
}

void main(){
	char c;
	int estado = 1;
	while (1==1) {
		c = getchar();
		if 	(c=='a' || c=='b') // alfabeto 
			estado = siguiente(estado, c); 
		else if (c=='\n') 
		{
			if (final(estado)) 
				printf ("SI\n");
			else 		
				printf ("NO\n"); 
			break;
		} 
		else { // símbolo extraño
			printf ("NO (símbolo de entrada inesperado) \n");
			break; 
		}
	}
}

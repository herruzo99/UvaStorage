#include <stdio.h>
/* RECONOCEDOR SIMPLE para a(a|b)*
   DETERMINISTA COMPLETO
   Alfabeto de entrada: a, b. Si hay símbolos extraños, el programa muestra un mensaje
   La entrada termina con fin de línea (\n)
*/

int num (char c) {
	// devuelve el número asociado al carácter, empezando por 0
	return c-'a';
}

int siguiente (int q, char c) {
	int t[3][2] =  { {2,3},{2,2},{3,3}};
	// PRE: c pertenece al alfabeto de entrada
	//	q es un número de estado válido 
	//	(es decir: 1<=q<=3 0<=num(c)<=1) 
	return t[q-1][num(c)]; // numeración de estados desde 1, tabla desde 0
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

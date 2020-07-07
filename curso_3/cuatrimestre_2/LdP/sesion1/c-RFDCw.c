#include <stdio.h>
/* RECONOCEDOR SIMPLE para a(a|b)*
   DETERMINISTA COMPLETO
   Alfabeto de entrada: a, b, otro
   La entrada termina con fin de línea (\n)
*/
void main(){
	char c;
	int estado = 1;
	while (1==1) {
		switch (estado){
			case 1:
				c = getchar();
				if 	(c=='a') 
					estado = 2; 
				else if (c=='b') 
					estado = 3;
				else if (c=='\n') 
				{
					printf ("NO\n"); 
					return;
				} 
				else estado = 3; // símbolo extraño
				break;
			case 2:
				c = getchar();
				if 	(c=='a') 
					estado = 2; 
				else if (c=='b') 
					estado = 2;
				else if (c=='\n') 
				{
					printf ("SI\n"); 
					return;
				} 
				else estado = 3; // símbolo extraño
				break;
			case 3:
				c = getchar();
				if 	(c=='a') 
					estado = 3; 
				else if (c=='b') 
					estado = 3;
				else if (c=='\n') 
				{
					printf ("NO\n"); 
					return;
				} 
				else estado = 3; // símbolo extraño
				break;
		 }
	}
}

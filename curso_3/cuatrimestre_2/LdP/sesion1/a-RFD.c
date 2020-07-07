#include <stdio.h>
/* RECONOCEDOR SIMPLE para a(a|b)*
   DETERMINISTA no completo
   Alfabeto de entrada: a, b, otro
   La entrada termina con fin de línea (\n)
*/
void main(){
	char c;
q1:	c = getchar();
	if 	(c=='a') 
		goto q2; 
	else if (c=='\n') 
		{
		printf ("NO"); 
		goto FIN;
		}
	goto FIN;

q2: 	c = getchar();
	if 	(c=='a')
		goto q2;
	else if (c=='b')
		goto q2;
	else if (c=='\n')
		{
		printf ("SI"); 
		goto FIN;
		}
	goto FIN;

FIN: 	printf ("\n");
}

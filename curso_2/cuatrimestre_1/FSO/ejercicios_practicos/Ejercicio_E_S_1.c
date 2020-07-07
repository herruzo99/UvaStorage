#include <stdio.h>

 int main( void )

{

char a = 'o', b = 'c', c='a';
char letra;
printf("Dime una letra: ");
scanf("%c", &letra);

if(letra ==a) printf("Has acertado la o!\n");
else if(letra ==b) printf("Has acertado la c!\n");
else if(letra ==c) printf("Has acertado la a!\n");
else printf("Vaya, no acertate\n");
return 0;
}

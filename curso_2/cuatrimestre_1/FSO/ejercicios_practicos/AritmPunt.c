// Demostraci�n de c�mo funciona la aritm�tica de Punteros.
#include <stdio.h>

#define TAM 10

int main (){
  long double a[TAM];
  int i;
  long double *ptr;
  // Atenci�n: Los punteros son direcciones de memoria, y como tal,
  // son n�meros enteros sin signo.

  // Aunque normalmente los punteros se escriben en base 16 (hexadecimal),
  // aqu� por claridad utilizaremos base 10.

  for (i=0; i< TAM; i++){
    printf ("a[%d] --> Posici�n de memoria que ocupa %lu\n", i, (unsigned long)&(a[i]));
  }
  printf ("El tama�o de un item es %lu bytes\n", sizeof (long double) );

  printf ("Probemos la aritm�tica de Punteros\n");
  ptr=&a[0];
  printf ("El valor del puntero --> ptr=%lu\n", (unsigned long)ptr);
  for (i=0; i<TAM; i++){
	 printf (" ptr + %d = %lu + %d", i, (unsigned long)ptr, i);
	 printf (" = %lu\n", (unsigned long) ptr+i);
  }

  return 0;
}

#include <stdio.h>
#include <stdlib.h>

void invertir(int tam,long *punt){
  for(int i = 0; i < tam/2; i++){
    long tmp = *(punt + i);

    *(punt+i) = *(punt+ tam - i - 1);
    *(punt+ tam - i - 1) = tmp;
  }
}

int main(int arg_tam, char *arg[]){
  if (arg_tam != 2){
    printf("ERROR, solo hay que introducir un argumento\n");
    exit(1);
  }
  int num;
  if ( sscanf(arg[1], "%d", &num) != 1){
    printf("ERROR, argumento no es un int\n");
    exit(2);
  }
  long* punt = malloc(num* sizeof(long));

  for(int i = 0;i < num;i++){
    printf("Dame el valor de la posicion %d del array:\n", i + 1);
    scanf("%ld", punt+ i);

  }
    printf("El array vale: ");
  for(int i = 0; i < num; i++){

    printf("%ld ",*(punt+i));
  }
    printf("\n");
    invertir(num,punt);
    printf("El array vale: ");
  for(int i = 0; i < num; i++){

    printf("%ld ",*(punt+i));
  }
    printf("\n");
  return 0;
}

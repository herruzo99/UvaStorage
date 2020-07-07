#include <stdio.h>
#include <pthread.h>
#include <string.h>
int entero = 0;
char cadenas[3][80];

void *hilo(void *arg){
  int antiguo = entero;
  int nuevo;
  if (entero == 0){
    nuevo = entero = 8;
  }else{
     nuevo = ++entero;
  }
  sprintf(cadenas[*(int*)arg],"Mensaje del hilo %d. Valor anterio de la variable %d. Valor nuevo %d.",*(int*)arg, antiguo, nuevo);
  pthread_exit(0);
}

int main(){
  int NUM_HILOS = 3;
  pthread_t tid[NUM_HILOS];
  int I[NUM_HILOS];

  for(int i = 0; i < NUM_HILOS; i++){
    I[i] = i;
  }

  for(int i = 0; i < NUM_HILOS; i++){
    pthread_create ( &tid[i], NULL, hilo, (void*) &I[i]);
  }


  for(int i = 0; i < NUM_HILOS; i++){
    pthread_join (tid[i], NULL);
  }
  printf("Valor del entero: %d\n", entero);
  for(int i = 0; i < NUM_HILOS; i++){
    printf("Cadena %d: %s\n", i, cadenas[i]);
  }
}

#include <stdio.h>
#include <pthread.h>
#include <string.h>
#include <semaphore.h>

int entero = 0;
char cadenas[3][80];
sem_t mutex;
void *hilo(void *arg){
  sem_wait(&mutex);
  int antiguo = entero;
  if (entero == 0){
    entero = 8;
  }else{
    entero++;
  }
  sprintf(cadenas[*(int*)arg],"Mensaje del hilo %d. Valor anterio de la variable %d. Valor nuevo %d.",*(int*)arg, antiguo, entero);
  sem_post(&mutex);
  pthread_exit(0);
}

int main(){
  sem_init(&mutex, 0, 1);
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
  sem_destroy(&mutex);
}

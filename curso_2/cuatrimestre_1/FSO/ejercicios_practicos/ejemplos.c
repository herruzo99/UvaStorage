#include <stdio.h>
#include <pthread.h>
#include <string.h>
#include <semaphore.h>

int entero[5];
sem_t sem_prod;
sem_t sem_con;
void *productor(void *arg){
  int pos = 0;
  for(int i = 1; i <= 50; i++){
    sem_wait(&sem_prod);
    entero[pos] = i;
    sem_post(&sem_con);
  }
  pthread_exit(0);
}
void *consumidor(void *arg){

  pthread_exit(0);
}
int main(){
  sem_init(&sem_prod, 0, 5);
  sem_init(&sem_con, 0, 0);
  pthread_t productor;
  pthread_t consumidor;

  pthread_create ( &productor, NULL, productor, NULL);
  pthread_create ( &consumidor, NULL, consumidor, NULL);



  pthread_join (productor, NULL);
  pthread_join (consumidor, NULL);

  printf("Valor del entero: %d\n", entero);
  for(int i = 0; i < NUM_HILOS; i++){
    printf("Cadena %d: %s\n", i, cadenas[i]);
  }
  sem_destroy(&mutex);
}

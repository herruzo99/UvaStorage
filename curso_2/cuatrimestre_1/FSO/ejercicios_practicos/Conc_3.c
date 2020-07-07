#include <stdio.h>
#include <pthread.h>
int argext[2]={2,3};

void *hilo(void *arg){
  int num;
  num = *(int *)arg;
  num *= 100;
  for(int i = 0; i < 100; i++){
    printf("%d\n", num + i);
  }
  pthread_exit(0);
}

int main(){
  int NUM_HILOS = 4;
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
}

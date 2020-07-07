//Rebeca Hernando Brecht 12425053Q
//Juan Herruzo Herrero 71178315P

//Los semáforos mutexBuffer1 y mutexBuffer2 están implementados, a pesar de que
//para esta primera versión no hacen faltan, pensando en la segunda versión del
//programa ya que las variables que indican la posicon de escritura/lectura de
//los buffers habrá que ponerlas como variables compartidas.

#include <semaphore.h>
#include <stdio.h>
#include <string.h>
#include <pthread.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

//estrutura par almacenar el intervalo
struct par {
  int num1;
  int num2;
};

//recursos compartidos
struct par *buffer1;
char **buffer2; //TODO
int numPares;
int tamBuffer;
sem_t mutexBuffer1, mutexBuffer2; //para la versión 2
sem_t hayHuecoBuffer1, hayDatoBuffer1, hayHuecoBuffer2, hayDatoBuffer2;

//productor que crea los intervalos
void *productor(void *arg){
  int aux;
  int posicionEscritura = 0;
  int escriturasRestantes = numPares;
  while(escriturasRestantes > 0){
    struct par par;
    //crea intervalo
    par.num1 = rand() % 999999;
    par.num2 = rand() % 999999;
    //ordena intervalo
    if(par.num1 > par.num2){
      aux = par.num1;
      par.num1 = par.num2;
      par.num2 = aux;
    }

    sem_wait(&hayHuecoBuffer1);
    sem_wait(&mutexBuffer1);

    //Exclusión mutua.
    buffer1[posicionEscritura] = par;
    posicionEscritura = (posicionEscritura + 1) % tamBuffer;

    sem_post(&mutexBuffer1);

    escriturasRestantes--;
    sem_post(&hayDatoBuffer1);
  }

  pthread_exit(0);
}

//consumidor que calcula el número de primos del intervalor
void *calculadorPrimos(void *arg){
  int posicionLectura = 0;
  int lecturasRestantes = numPares;
  int posicionBuffer2 = 0;
  int primos = 0;

  while(lecturasRestantes > 0){
    primos = 0;
    sem_wait(&hayDatoBuffer1);
    sem_wait(&mutexBuffer1);

    //Exclusión mutua.
    struct par par = buffer1[posicionLectura];
    posicionLectura = (posicionLectura + 1) % tamBuffer;

    sem_post(&mutexBuffer1);
    sem_post(&hayHuecoBuffer1);
    for(int i = par.num1; i <= par.num2; i++){
      int primo = 1; //true
      int cont = 2; //no se puede dividir entre 0 y entre 1 siempre es divisible
      while( cont < sqrt(i) && primo){
        primo = i % cont == 0 ? 0 : 1;
        cont ++;
      }
    if(primo==1){
      primos++;
    }
  }
    lecturasRestantes--;

    //guarda en el buffer2
    sem_wait(&hayHuecoBuffer2);
    sem_wait(&mutexBuffer2);

    //Exclusión mutua.
    char aux[50];
    sprintf(aux, "Entre %d y %d hay %d números primos.\n", par.num1, par.num2, primos);
    memcpy(buffer2[posicionBuffer2], aux,sizeof(char)*50);

    posicionBuffer2=(posicionBuffer2+1)%5;

    sem_post(&mutexBuffer2);
    sem_post(&hayDatoBuffer2);
  }

  pthread_exit(0);
}

//consumidor que guarda en el resultado en un fichero
void *guardarFichero(void *arg){
  FILE *ficheroResultado;
  int lecturasRestantes = numPares;
  int posicionBuffer2 =0;
  char *dato;

  //crear fichero en modo escritura
  if ((ficheroResultado = fopen("ficheroResultado.txt", "w")) == NULL){
    printf ("ERROR No se puede crear ficheroResultado.txt .\n");
    exit (1);
  }

  while(lecturasRestantes > 0){

    sem_wait(&hayDatoBuffer2);
    sem_wait(&mutexBuffer2);

    //Exclusión mutua.
    dato = buffer2[posicionBuffer2];
    posicionBuffer2 = (posicionBuffer2+1)%5;

    sem_post(&mutexBuffer2);
    sem_post(&hayHuecoBuffer2);
    fprintf(ficheroResultado,"%s", dato);
    lecturasRestantes--;

  }
  fclose (ficheroResultado);
  pthread_exit(0);
}

//main del programa
int main(int argc, char *argv[]){

  srand(time(NULL));

  if(argc != 3){
    printf("Número de argumentos erroneo.\n");
    return(1);
  }

  sscanf(argv[1],"%d", &tamBuffer);
  sscanf(argv[2],"%d", &numPares);
   if((buffer1 = (struct par*)malloc(numPares * sizeof(struct par))) == NULL){
     printf ("ERROR No se pudo crear la memoria dinámica.\n");
     exit (2);
  }

  buffer2 = (char**)malloc(5 * sizeof(char*)); //Mejor doble y esta definido en compilación
	
	for(int i = 0; i < 5; i++){
		buffer2[i] = (char*)malloc(5 * sizeof(char)* 50);
	}

  //Inicialización de los semaforos.
  sem_init(&mutexBuffer1,0,1);
  sem_init(&mutexBuffer2,0,1);
  sem_init(&hayDatoBuffer1,0,0);
  sem_init(&hayHuecoBuffer1,0,tamBuffer);
  sem_init(&hayDatoBuffer2,0,0);
  sem_init(&hayHuecoBuffer2,0,5);

  //Declaración de hilos.
  pthread_t productorHilo;
  pthread_t calculadorPrimosHilo;
  pthread_t guardarFicheroHilo;

  //Creación de hilos.
  pthread_create (&productorHilo, NULL, productor, (void*) NULL);
  pthread_create(&calculadorPrimosHilo, NULL, calculadorPrimos, (void*) NULL);
  pthread_create(&guardarFicheroHilo, NULL, guardarFichero, (void*) NULL);


  //Espera a cierre de hilos.
  pthread_join(productorHilo, NULL);
  pthread_join(calculadorPrimosHilo, NULL);
  pthread_join(guardarFicheroHilo, NULL);
  return(0);
}

#include <stdio.h>
#include <math.h>
#include <stdlib.h>

double media(int tam, double *array);
double des_tip(int tam,double media, double *array);
double maximo(int tam, double *array);

int main(int argc, char **argv){
  int tam = argc - 1;
  if (tam < 2){
    printf("ERROR: Minimo 2 números reales\n");
    exit(1);
  }
  double *array;
  array = malloc(sizeof(double) * (argc - 1));
  for(int i = 1; i < argc; i++){

    if(sscanf(argv[i], "%lf", &array[i-1]) !=1){
      printf("ERROR: Alguno de los datos introducidos no era un número real\n");
      exit(2);
    }
  }
  double med = media(tam, array);
  double max = maximo(tam, array);
  double des = des_tip(tam, med, array);
  FILE *salida;
  salida = fopen("salida.txt", "w");
  fprintf(salida,"Media: %lf\n", med);
  fprintf(salida,"Desviación típica: %lf\n",des);
  fprintf(salida,"Máximo: %lf\n", max);




  return 0;
}

double media(int tam, double *array){
  double media = 0;
  for(int i = 0; i < tam; i++){
    media += array[i];
  }
  media = media/tam;
  return media;
}

double maximo(int tam, double *array){
  double max = 0;
  for(int i = 0; i < tam; i++){
    if(array[i] > max){
      max = array[i];
    }
  }
  return max;
}

double des_tip(int tam, double media,double *array){
  double desviacion = 0;
  for(int i = 0; i < tam; i++){
    desviacion += pow(array[i],2);
  }
  desviacion = desviacion/tam;
  desviacion -= pow(media,2);
  return sqrt(desviacion);
}

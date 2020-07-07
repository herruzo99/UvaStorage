#include<stdio.h>
#include<stdlib.h>
/* 1. Include de biblioteca OpenMP */
#include<omp.h>
#define SIZE	300000000
int main() {

	int *v = (int *)malloc( SIZE * sizeof(int) );
	int i;
	int suma = 0;

	/* 1. Paralelizar la inicialización */
#pragma omp parallel for shared(v)
	for ( i=0; i<SIZE; i++ ) v[i] = i;

	/* 2. Paralelizar la suma, utilizando la cláusula de reducción */
#pragma omp parallel for reduction(+:suma) shared(v)
	for ( i=0; i<SIZE; i++ ) suma = ( suma + v[i] ) % 65535;
	suma = suma  % 65535;
	printf( "Resultado final: %d\n", suma );
	return 0;
}

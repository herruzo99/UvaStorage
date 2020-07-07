#include<stdio.h>
#include<stdlib.h>
/* 1. Include de biblioteca OpenMP */
# include<omp.h>
#define SIZE	150000
int main() {
	double tiempoTot, tiempoPar;
	tiempoTot = omp_get_wtime();
	int *v = (int*)malloc( SIZE * sizeof(int) );
	int i, j;
	int suma = 0;

	/* Inicializar */
	#pragma omp parallel for shared( v ), private( i )
	for ( i=0; i<SIZE; i++ ) v[i] = 0;
	tiempoPar = omp_get_wtime();
	/* 2. Directiva de bucle paralelo: Con vector v compartido e índice i privado */
	#pragma omp parallel for shared( v ), private( j )
	for ( i=0; i<SIZE; i++ ) 
			for ( j=0; j<SIZE; j++ ) v[j] = v[j] + i + j;
	
	tiempoPar = omp_get_wtime() - tiempoPar;
	/* Suma secuencial */
	for ( i=0; i<SIZE; i++ ) suma = ( suma + v[i] ) % 65535;
	printf( "Resultado final: %d\n", suma );
	tiempoTot = omp_get_wtime() - tiempoTot;
	printf("Total: %lf, Paralelo: %lf\n", tiempoTot, tiempoPar );
	return 0;
}

#include<stdio.h>
/* 1. Include de biblioteca OpenMP */
#include <omp.h>
int main() {

	int v[ 10 ];
	int i;

	/* Inicializar */
	for ( i=0; i<10; i++ ) v[i] = 0;

	/* 2. Directiva de paralelismo: Con vector v compartido */
	#pragma omp parallel shared (v) 
	{
	/* 3. Dentro de la región paralela: Cada thread escribe la posición
	 * 		de su identificador un 10 + su identificador */
	v[ omp_get_thread_num() ] = 10 + omp_get_thread_num();

	/* etc ... (Reescribir en paralelo) */
	}
	/* Escribir */
	for ( i=0; i<10; i++ ) printf( " %d", v[i] );
	printf( "\n" );

	return 0;
}

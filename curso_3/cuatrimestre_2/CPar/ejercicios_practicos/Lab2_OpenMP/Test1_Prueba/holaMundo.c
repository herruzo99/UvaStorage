#include<stdio.h>
/* 1. Include de biblioteca OpenMP */
#include<omp.h>
int main() {
	/* 2. Directiva de paralelismo */
	# pragma omp parallel
	/* 3. Escribir el identificador de thread y la cantidad de threads */
	printf("Hola mundo. Soy %d de %d \n", omp_get_thread_num(), omp_get_num_threads());

	return 0;
}

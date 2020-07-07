//
// Suma los cuadrados de los n primeros numeros naturales
//
// Cada proceso calcula un cuadrado, el primer proceso recibe todos y los suma
//

#include<stdio.h>
#include<stdlib.h>
#include<mpi.h>

int main(int argc, char *argv[]) {

	MPI_Init( &argc, &argv );

	// 1. Obtener datos del proceso
	int num_procs, my_rank;
	char maquina[MPI_MAX_PROCESSOR_NAME];
	int maquina_len;
	MPI_Get_processor_name( maquina, &maquina_len );
	MPI_Comm_size( MPI_COMM_WORLD, &num_procs );
	MPI_Comm_rank( MPI_COMM_WORLD, &my_rank );
	//printf("Proceso: %d de %d, en maquina: %s\n", my_rank, num_procs, maquina );

	// 2. Todos calculan el cuadrado de su id + 1
	int cuadrado = (my_rank + 1) * (my_rank+1);

	// 3. Todos menos el primero mandan el cuadrado calculado al primero
	int tag = 2000;
	if ( my_rank > 0 ) {
		MPI_Send( &cuadrado, 1 , MPI_INT, 0 , tag, MPI_COMM_WORLD );
	}
	// 4. El primero va recibiendo de todos los demas y sumando
	else {
		MPI_Status status;
		int dato_recibido;
		int i;
		int result = cuadrado;

		for (i= 1 ; i< num_procs ; i++) {
			MPI_Recv( &dato_recibido, 1, MPI_INT, i , tag, MPI_COMM_WORLD, &status );
			result += dato_recibido;
		}
		printf("Resultado suma cuadrados: %d\n", result );
	}

	MPI_Finalize();

	return 0;
}

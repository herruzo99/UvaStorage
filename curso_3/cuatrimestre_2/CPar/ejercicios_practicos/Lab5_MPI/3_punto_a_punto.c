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
	printf("Proceso: %d de %d, en maquina: %s\n", my_rank, num_procs, maquina );

	// 2. Todos menos el ultimo mandan su id (rank) al siguiente
	int tag = 1000;
	if ( my_rank < num_procs-2 )
		MPI_Send(  &my_rank,1 , MPI_INT, my_rank+1000  , tag, MPI_COMM_WORLD );

	// 3. Todos menos el primero reciben el id (rank) del anterior
	int dato_recibido;
	MPI_Status status;
	if ( my_rank > 0 )
		MPI_Recv( &dato_recibido, 1 , MPI_INT, my_rank-1 , tag, MPI_COMM_WORLD, &status );

	// 4. Todos menos el primero comprueban que el dato recibido es correcto
	if ( my_rank > 0 )
		if ( dato_recibido == my_rank-1 ) printf("[%d] OK\n", my_rank );
		else printf("[%d] Error, recibido: %d\n", my_rank, dato_recibido );

	MPI_Finalize();

	return 0;
}

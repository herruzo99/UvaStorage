#include<stdio.h>
#include<mpi.h>

int main(int argc, char *argv[]) {

	MPI_Init( &argc, &argv );

	int num_procs, my_rank;
	char maquina[MPI_MAX_PROCESSOR_NAME];
	int maquina_len;

	MPI_Get_processor_name( maquina, &maquina_len );
	MPI_Comm_size( MPI_COMM_WORLD, &num_procs );
	MPI_Comm_rank( MPI_COMM_WORLD, &my_rank );

	printf("Proceso: %d de %d, en maquina: %s\n", my_rank, num_procs, maquina );

	MPI_Finalize();

	return 0;
}

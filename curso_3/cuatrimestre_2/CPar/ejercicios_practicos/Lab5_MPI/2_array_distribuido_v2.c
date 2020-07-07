#include<stdio.h>
#include<stdlib.h>
#include<mpi.h>

int main(int argc, char *argv[]) {

	MPI_Init( &argc, &argv );

	// 1. Leer argumento
	if (argc<2) {
		fprintf( stderr, "Usage: %s <size_array>\n", argv[0] );
		exit( EXIT_FAILURE );
	}
	int size = atoi( argv[1] );

	// 2. Obtener datos del proceso
	int num_procs, my_rank;
	char maquina[MPI_MAX_PROCESSOR_NAME];
	int maquina_len;
	MPI_Get_processor_name( maquina, &maquina_len );
	MPI_Comm_size( MPI_COMM_WORLD, &num_procs );
	MPI_Comm_rank( MPI_COMM_WORLD, &my_rank );
	printf("Proceso: %d de %d, en maquina: %s\n", my_rank, num_procs, maquina );

	// 2. Crear array distribuido (cada proceso un trozo)
	// 2.1. Calcular cuanto espacio necesita cada proceso
	int my_size = size / num_procs;
	int my_begin = my_size * my_rank;

	if(my_rank < size - my_size*num_procs){
		my_size++;
	}
	// 2.2. Calcular donde empieza cada proceso con respecto al hipotetico array global


	int *array_d = (int *)malloc( sizeof(int) * my_size );
	if ( array_d == NULL ) {
		fprintf( stderr, "Error: Reservando memoria para array distribuido\n");
		exit( EXIT_FAILURE );
	}

	// ATENCION:
	// Mis indices locales siempre empiezan en 0, aunque representen otra parte del array global

	// 3. Inicializar mi parte
	int i;
	for( i = 0; i < my_size; i++ ) {
		array_d[i] = i + my_begin;
	}

	// 4. Escribir mi parte
	for(  i = 0; i < my_size; i++ ) 
		printf("[%d] Pos: %d = %d\n", my_rank, i, array_d[i] );

	MPI_Finalize();

	return 0;
}

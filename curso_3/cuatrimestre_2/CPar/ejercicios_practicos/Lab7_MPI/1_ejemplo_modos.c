/*
 * Ejemplo modos de comunicación
 * Computacion Paralela, Grado de Ing. Informatica
 * Universidad de Valladolid
 *
 * (c) 2020, Arturo Gonzalez-Escribano
 */
#include<stdio.h>
#include<stdlib.h>
#include<mpi.h>

int main(int argc, char *argv[]) {

	int tag = 32;
	int rank;
	int nprocs;

	// Initialize MPI
	MPI_Init( &argc, &argv );
	MPI_Comm_size( MPI_COMM_WORLD, &nprocs );
	MPI_Comm_rank( MPI_COMM_WORLD, &rank );
	MPI_Status stat;
	// Read arguments
	if ( argc < 3 && rank == 0 ) {
		fprintf(stderr, "Usage: %s <size> <jump>\n", argv[0] );
		MPI_Abort( MPI_COMM_WORLD, -1 );
	}
	int size = atoi( argv[1] );
	int jump = atoi( argv[2] );

	// Allocate memory
	double *data = (double *)malloc( size * sizeof(double) );
	double *data2 = (double *)malloc( size * sizeof(double) );
	if ( data == NULL || data2 == NULL ) {
		fprintf(stderr, "Rank %d, Error allocating memory for %d elements\n", rank, size );
		MPI_Abort( MPI_COMM_WORLD, -1 );
	}

	// Initialize array 1: Multiply rank by size and add the index
	int i;
	for (i=0; i<size; i++) data[i] = rank * size + i;

	// Data should jump as if it would be rotated "jump" times
	// Compute the ranks of processes to send data, and to receive from
	int rank_to = (rank+jump)%nprocs;
	int rank_from = (rank-(jump%nprocs)+nprocs)%nprocs;

	// Check that the ranks are correct
	printf( "Rank[%d], to: %d, from: %d\n", rank, rank_to, rank_from );
	// Ensure that the printf is sent to terminal now
	fflush( stdout );
	MPI_Request req;
	// Send data array to the "rank_to" process
	MPI_Isend(data, size, MPI_DOUBLE, rank_to, tag, MPI_COMM_WORLD, &req);
	// Receive in data2 array from the "rank_from" process
	MPI_Recv(data2, size, MPI_DOUBLE, rank_from, tag, MPI_COMM_WORLD, &stat);
	// Check results
	printf( "Rank[%d], first: %lf, last: %lf\n", rank, data2[0], data2[size-1] );

	// End program
	//MPI_Wait(&req, &stat);
	MPI_Finalize();
	return 0;
}

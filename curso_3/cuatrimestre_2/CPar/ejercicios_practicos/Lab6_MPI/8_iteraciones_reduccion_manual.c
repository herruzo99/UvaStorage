#include<stdio.h>
#include<math.h>
#include<mpi.h>
#include <string.h>
#include <stdlib.h>

#define	ITER	100

int main(int argc, char *argv[]) {

	MPI_Init( &argc, &argv );

	int orirank, rank, nprocs;
	MPI_Comm_rank( MPI_COMM_WORLD, &rank );
	orirank = rank;
	MPI_Comm_size( MPI_COMM_WORLD, &nprocs );
	MPI_Comm_set_errhandler(MPI_COMM_WORLD, MPI_ERRORS_RETURN);
	MPI_Comm newcomm;
	MPI_Comm_split(MPI_COMM_WORLD,rank%2,rank/2,&newcomm);
	MPI_Comm_rank( newcomm, &rank );

	MPI_Status stat;

	double total = 0;
	double total_parcial = 0;
	double value = 1;
	double recv_value;
	int tag = 1000;

	double values[nprocs];

	MPI_Barrier( MPI_COMM_WORLD );
	double time = MPI_Wtime();

	// Multiples fase de cálculo y comunicación
	int i;
	for( i=0; i<ITER; i++ ) {
		// Cálculo
		value = sin( value );
		int ierr;
		ierr = MPI_Reduce( &value, &total_parcial, 1, MPI_DOUBLE, MPI_SUM, 0, newcomm );

		if (ierr != MPI_SUCCESS){
			char *string;
			int * len;
			string = malloc(sizeof(char)* MPI_MAX_ERROR_STRING);
			MPI_Error_string(ierr, string, len);
			fprintf(stderr, "ERROR %s\n", string);
			MPI_Abort(MPI_COMM_WORLD, -1);
		}
		if ( rank == 0 ) {
		total +=total_parcial;
		}
	}


	MPI_Barrier( newcomm );
	time = MPI_Wtime() - time;

	// El 0 escribe los resultados de valor y tiempo
	if ( rank == 0 ) {
		printf("Total: %lf Time: %lf Rank: %d\n", total, time, orirank );
	}

	MPI_Finalize();
	return 0;
}

#include<stdio.h>
#include<math.h>
#include<mpi.h>
#include <string.h>
#include <stdlib.h>

#define	ITER	7000

int main(int argc, char *argv[]) {

	MPI_Init( &argc, &argv );

	int rank, nprocs;
	MPI_Comm_rank( MPI_COMM_WORLD, &rank );
	MPI_Comm_size( MPI_COMM_WORLD, &nprocs );
	MPI_Comm_set_errhandler(MPI_COMM_WORLD, MPI_ERRORS_RETURN);

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
		ierr = MPI_Allreduce( &value, &total_parcial, 1, MPI_DOUBLE, MPI_SUM, MPI_COMM_WORLD );

		if (ierr != MPI_SUCCESS){
			char *string;
			int * len;
			string = malloc(sizeof(char)* MPI_MAX_ERROR_STRING);
			MPI_Error_string(ierr, string, len);
			fprintf(stderr, "ERROR %s\n", string);
			MPI_Abort(MPI_COMM_WORLD, -1);
		}
		total +=total_parcial;

	}
		

	MPI_Barrier( MPI_COMM_WORLD );
	time = MPI_Wtime() - time;

	// El 0 escribe los resultados de valor y tiempo
		printf("Rank:%d, Total: %lf\n",rank, total );
		printf("Time: %lf\n", time );


	MPI_Finalize();
	return 0;
}

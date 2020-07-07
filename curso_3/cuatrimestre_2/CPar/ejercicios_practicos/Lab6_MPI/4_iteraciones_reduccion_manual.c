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

	double value = 1;
	double recv_value;
	int tag = 1000;

	double values[nprocs];

	MPI_Barrier( MPI_COMM_WORLD );
	double time = MPI_Wtime();

	// Multiples fase de cálculo y comunicación
	int i;
	double value_total = 0;
	for( i=0; i<ITER; i++ ) {
		// Cálculo
		value = sin( value );
		value_total +=value;
	}


			int ierr = MPI_Gather( &value_total, 1, MPI_DOUBLE,&values,1, MPI_DOUBLE, 0, MPI_COMM_WORLD );
			if (ierr != MPI_SUCCESS){
				char *string;
				int * len;
				string = malloc(sizeof(char)* MPI_MAX_ERROR_STRING);
				MPI_Error_string(ierr, string, len);
				fprintf(stderr, "ERROR %s\n", string);
				MPI_Abort(MPI_COMM_WORLD, -1);
			}

		if ( rank == 0 ) {
			int j;
			// El 0 recibe mensajes de cada uno y acumula los valores
			for( j = 0; j < nprocs; j++) {

				total = total + values[j];
			}
			// El 0 también suma su propio valor

		}
	

	MPI_Barrier( MPI_COMM_WORLD );
	time = MPI_Wtime() - time;

	// El 0 escribe los resultados de valor y tiempo
	if ( rank == 0 ) {
		printf("Total: %lf\n", total );
		printf("Time: %lf\n", time );
	}

	MPI_Finalize();
	return 0;
}

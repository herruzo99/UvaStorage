#include<stdio.h>
#include<math.h>
#include<mpi.h>

#define	ITER	7000

int main(int argc, char *argv[]) {

	MPI_Init( &argc, &argv );

	int rank, nprocs;
	MPI_Comm_rank( MPI_COMM_WORLD, &rank );
	MPI_Comm_size( MPI_COMM_WORLD, &nprocs );

	MPI_Status stat;

	double total = 0;

	double value = 1;
	double recv_value;
	int tag = 1000;
	
	MPI_Barrier( MPI_COMM_WORLD );
	double time = MPI_Wtime();

	// Multiples fase de cálculo y comunicación
	int i;
	for( i=0; i<ITER; i++ ) {
		// Cálculo
		value = sin( value );


		// Comunicación
		// Todos menos el 0 mandan su valor
		if ( rank > 0 ) {
			MPI_Send( &value, 1, MPI_DOUBLE, 0, tag, MPI_COMM_WORLD );
		}
		else {
			int j;
			// El 0 recibe mensajes de cada uno y acumula los valores
			for( j = 1; j < nprocs; j++ ) {
				MPI_Recv( &recv_value, 1, MPI_DOUBLE, j,  tag, MPI_COMM_WORLD, &stat );
				total = total + recv_value;
			}
			// El 0 también suma su propio valor
			total = total + value;
		}
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

/*
 * Ejemplo tipos derivados struct
 * Computacion Paralela, Grado de Ing. Informatica
 * Universidad de Valladolid
 *
 * (c) 2020, Arturo Gonzalez-Escribano
 */
#include<stdio.h>
#include<stdlib.h>
#include<stddef.h>
#include<mpi.h>

typedef struct {
	float coord[2];
	int sizes[2];
	char rank;
} Rectangle;

int main(int argc, char *argv[]) {

	int rank;
	int nprocs;
	int tag = 32;
	// Initialize MPI
	MPI_Init( &argc, &argv );
	MPI_Comm_size( MPI_COMM_WORLD, &nprocs );
	MPI_Comm_rank( MPI_COMM_WORLD, &rank );

	// Create datatype for Rectangle
	// Number of field blocks
	int fields = 3 ;
	// Number of elements per block
	int array_of_blocklengths[] = { 2,2,1 };
	// Block displacements
	MPI_Aint array_of_displacements[] = {
		offsetof(Rectangle, coord),
		offsetof(Rectangle, sizes),
		offsetof(Rectangle, rank)
		};
	// Block types
	MPI_Datatype array_of_types[] = { MPI_FLOAT, MPI_INT, MPI_CHAR };
	MPI_Datatype MPI_Rectangle, MPI_RectangleExt, MPI_RectangleArr;

	// Create basic fields structure
	MPI_Type_create_struct(fields, array_of_blocklengths, array_of_displacements, array_of_types, &MPI_Rectangle );

	// Resize to cover alignment extent
	MPI_Aint lb, extent;
	MPI_Type_get_extent( MPI_Rectangle, &lb, &extent );
	MPI_Type_create_resized( MPI_Rectangle, lb, extent, &MPI_RectangleExt );
	MPI_Type_commit( &MPI_RectangleExt );
	MPI_Type_contiguous( nprocs, MPI_RectangleExt, &MPI_RectangleArr);
	MPI_Type_commit( &MPI_RectangleArr );

	// Random values for the local variable on each processor
	srand48( 43874 + rank * 7 );
	Rectangle my_value;
	my_value.coord[0] = (float)( drand48() * 100 );
	my_value.coord[1] = (float)( drand48() * 100 );
	my_value.sizes[0] = (int)( drand48() * 25 );
	my_value.sizes[1] = (int)( drand48() * 20 );
	my_value.rank = rank;
	// Gather all values in last rank, and last rank sends it to rank 0
	if ( rank == nprocs-1 ) {
		Rectangle array[nprocs];
		MPI_Gather( &my_value, 1, MPI_RectangleExt, &array, 1, MPI_RectangleExt, nprocs-1, MPI_COMM_WORLD );
		MPI_Send( array, 1, MPI_RectangleArr, 0, tag, MPI_COMM_WORLD );

	}
	else {
		MPI_Gather( &my_value, 1, MPI_RectangleExt, NULL, 1, MPI_RectangleExt, nprocs-1, MPI_COMM_WORLD );
	}
	// Rank 0 receives the gathered array from the last process
	if ( rank == 0 ) {
		Rectangle array[nprocs];
		MPI_Status stat;
		MPI_Recv( array, 1, MPI_RectangleArr,nprocs-1 , tag, MPI_COMM_WORLD, &stat);

		// Check results
		int i;
		for (i=0; i<nprocs; i++) {
			printf("Rectangle( %f,%f, %d,%d, %d )\n",
				array[i].coord[0],
				array[i].coord[1],
				array[i].sizes[0],
				array[i].sizes[1],
				array[i].rank
			);
		}
	}

	// Free resources and end program
	MPI_Type_free(  &MPI_RectangleExt );
	MPI_Type_free(  &MPI_RectangleArr );
	MPI_Finalize();
	return 0;
}

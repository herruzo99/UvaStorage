
#include<stdio.h>
#include<stdlib.h>

#define SEED	6834723

int main(int argc, char *argv[]) {
	int i,j,k;

	if ( argc < 2 ) {
		fprintf(stderr, "Usage: %s <size>\n", argv[0] );
		exit(-1);
	}
	int size = atoi( argv[1] );

	double A[ size ][ size ];
	double B[ size ][ size ];
	double C[ size ][ size ];

	srand48( SEED );

	for (i=0; i<size; i++)
		for (j=0; j<size; j++) {
			C[i][j] = 0;
			A[i][j] = drand48();
			B[i][j] = drand48();
		}

	for (i=0; i<size; i++)
		for (j=0; j<size; j++) 
			for (k=0; k<size; k=k+1) 
				C[i][j] = C[i][j] + A[i][k] * B[k][j];

	printf("Fin: %lf\n", C[0][0]);
	return 0;

}

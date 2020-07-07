
#include<stdio.h>
#include<stdlib.h>

#define SIZE_MAT	1533
#define SIZE_ARRAY	1536
#define	TILESIZE	16
#define SEED	6834723

int main() {
	int i,j,k;

	double A[ SIZE_ARRAY ][ SIZE_ARRAY ];
	double B[ SIZE_ARRAY ][ SIZE_ARRAY ];
	double C[ SIZE_ARRAY ][ SIZE_ARRAY ];

	srand48( SEED );

	for (i=0; i<SIZE_MAT; i++)
		for (j=0; j<SIZE_MAT; j++) {
			C[i][j] = 0;
			A[i][j] = drand48();
			B[i][j] = drand48();
		}

	/* RECORRER BLOQUES */
	int numTiles = SIZE_MAT/TILESIZE;
	int ti, tj, tk;
	for (ti=0; ti<numTiles; ti++)
			for (tk=0; tk<numTiles; tk++)
		for (tj=0; tj<numTiles; tj++)
				/* MULTIPLICACION DE BLOQUE x BLOQUE */
				for (i=0; i<TILESIZE; i++)
					for (j=0; j<TILESIZE; j++) 
						for (k=0; k<TILESIZE; k++) 
							C[ti*TILESIZE+i][tj*TILESIZE+j] = 
									C[ti*TILESIZE+i][tj*TILESIZE+j] +
									A[ti*TILESIZE+i][tk*TILESIZE+k] *
									B[tk*TILESIZE+k][tj*TILESIZE+j];

	printf("Fin: %lf", C[0][0]);
	return 0;

}

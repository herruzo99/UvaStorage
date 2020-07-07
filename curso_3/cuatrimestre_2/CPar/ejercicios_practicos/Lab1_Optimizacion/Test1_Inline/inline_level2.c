
#include<stdio.h>

int main() {

	double i;
	double var = 457;

	for( i=0; i<500000000; i++ ) {
		double temp;
		if (var   == 0) temp  = 0; else temp  = var       - 1; /* (1) */
		if (0     == 0) temp += 0; else temp += 0         - 1; /* (2) */
		if (var+1 == 0) temp += 0; else temp += (var + 1) - 1; /* (3) */
		var = temp;
	}

	printf("Resultado: %lf\n", var );

	return 0;
}

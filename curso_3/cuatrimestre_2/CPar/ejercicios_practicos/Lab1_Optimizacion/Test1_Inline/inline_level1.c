#include<stdio.h>

double f(double y) {
	double temp;
	if (y   == 0) temp  = 0; else temp  = y       - 1; /* (1) */
	if (0   == 0) temp += 0; else temp += 0       - 1; /* (2) */
	if (y+1 == 0) temp += 0; else temp += (y + 1) - 1; /* (3) */
	return temp;
}

int main() {

	double i;
	double var = -103456;

	for( i=0; i<500000000; i++ ) 
		var = f(var);

	printf("Resultado: %lf\n", var );

	return 0;
}


#include<stdio.h>

double f(double);

int main() {

	double i;
	double tmp = -103456;

	for( i=0; i<500000000; i++ ) 
		tmp = f(tmp);

	printf("Resultado: %lf\n", tmp );

	return 0;
}

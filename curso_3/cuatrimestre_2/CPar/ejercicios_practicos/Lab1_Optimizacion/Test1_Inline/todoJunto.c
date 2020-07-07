#include<stdio.h>

double pred(double x) {
    if (x == 0)
       return 0;
    else
       return x - 1;
}


double f(double y) {
     return pred(y) + pred(0) + pred(y+1);
}

int main() {

	int i;
	double tmp = -103456;

	for( i=0; i<500000000; i++ ) 
		tmp = f(tmp);

	printf("Resultado: %lf\n", tmp );

	return 0;
}

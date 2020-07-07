	int nlineas=0;
	int ellas = 0, las = 0;
%%
ella	ellas++;
la	las++;
[a-zA-Z]+	;
.	;
\n	nlineas++;
%%
int main(){
	yylex();
	printf("%d\n",nlineas);
	printf("%d %d\n", ellas,las);
}

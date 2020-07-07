%{
 #include <stdio.h>
 int  yyerror(char *err) {
	fprintf(stderr, "%s\n",err);
 }
 int yylex();
%}
%token NUM 
%%
p	: expr '\n' {printf ("correcto\n");} 
	;
expr 	: expr '+' expr {printf ("E -> E + E \n");}
	| expr '-' expr {printf ("E -> E - E \n");}
	| '(' expr ')'  {printf ("E -> ( E ) \n");}
	| NUM 		{printf ("E -> NUM \n");}
	;
%%
int main() { yyparse(); }

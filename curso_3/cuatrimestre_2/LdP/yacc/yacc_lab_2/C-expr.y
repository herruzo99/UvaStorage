%{
 #include <stdio.h>
 int  yyerror(char *err) {
	fprintf(stderr, "%s\n",err);
 }
 int yylex();
%}
%token NUM 
%%
p	: expr {printf ("correcto\n");} '\n' 
	;
expr 	: expr '+' term 
	| expr '-' term 
	| term 
	;
term 	: '(' expr ')' 
	| NUM 
	;
%%
int main() { yyparse(); }

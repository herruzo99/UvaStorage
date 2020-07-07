
%{
#include <stdio.h>
#include <string.h>
int  yyerror(char *err) {
	fprintf(stderr, "%s\n",err);
 }
int yylex();
%}

%union{
	char literal[100];
} 
%token <literal> NUM 
%type <literal> expr term
%%
s	: s p
	|
	;
p	: expr  '\n' {printf ("Resultado: %s\n", $1); }
	;
expr 	: expr '+' term { 
			strcpy ($$, $1);
			strcat ($$, " ");
			strcat ($$, $3);
			strcat ($$, " + "); 
			}
	| expr '-' term {
			strcpy ($$, $1);
			strcat ($$, " ");
			strcat ($$, $3);
			strcat ($$, " - "); 
			}
	| term {strcpy($$, $1);}
	;
term 	: '(' expr ')' {strcpy($$ , $2);}
	| NUM {strcpy($$, $1);}
	;
%%
int main() {
	yyparse();
}

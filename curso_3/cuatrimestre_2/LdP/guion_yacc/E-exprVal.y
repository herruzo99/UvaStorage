%{
 #include <stdio.h>
 int yyerror(char *err) { fprintf(stderr, "%s\n",err); }
 int yylex();
 double variables[26];
%}
%union {
	double valor;
	int id_variable;
}
%type <valor> expr una term prio
%token <valor> NUM 
%token PRINT LET
%token <id_variable> VARIABLE
%%
s	: s p
	|
	;
p	: PRINT expr  '\n' {printf ("Resultado: %lf\n", $2); }
	| LET VARIABLE '=' expr { variables[$2]=$4;	}
  	| '\n'
	;
expr 	: expr '+' prio {$$ = $1 + $3;}
	| expr '-' prio {$$ = $1 - $3; }
	| prio {$$ = $1; }
	;
prio	: prio '*' una {$$ = $1 * $3;}
     	| prio '/' una {$$ = $1 / $3; }
	| una
	;
una	: '-' term {$$ = - $2;}
    	| '+' term {$$ = $2;}
    	| term
    	;	
term 	: '(' expr ')' {$$ = $2;}
	| NUM 
	| VARIABLE {$$ = variables[$1];}
	;
%%
int main() { yyparse(); }

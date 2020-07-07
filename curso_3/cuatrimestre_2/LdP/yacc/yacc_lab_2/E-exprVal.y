%{
 #include <stdio.h>
 int yyerror(char *err) { fprintf(stderr, "%s\n",err); }
 int yylex();
%}
%token NUM 
%%
s	: s p
	|
	;
p	: expr  '\n' {printf ("Resultado: %d\n", $1); }
	;
expr 	: expr '+' term {$$ = $1 + $3; }
	| expr '-' term {$$ = $1 - $3; }
	| term {$$ = $1;}
	;
term 	: '(' expr ')' {$$ = $2;}
	| NUM 
	;
%%
int main() { yyparse(); }

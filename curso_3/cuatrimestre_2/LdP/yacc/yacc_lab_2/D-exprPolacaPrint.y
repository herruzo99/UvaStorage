%{
 #include <stdio.h>
int   yyerror(char *err) { fprintf(stderr, "%s\n",err); }
int yylex();
%}
%token  NUM 
%%
s	: s p
	|
	;
p	: expr  '\n' {printf ("\n"); }
	;
expr 	: expr '+' term  {printf ("+");}
	| expr '-' term {printf ("-");}
	| term 
	;
term 	: '(' expr ')' 
	| NUM 
	;
%%
int main() {
	yyparse();
}

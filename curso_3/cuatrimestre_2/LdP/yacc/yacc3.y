
%{
#include <stdio.h>
 int yyerror (char* s) {
	fprintf(stderr, "%s\n", s);
 }
#include "lex.yy.c"
%}
%%
S	: 'h' O 'l' 'a' '\n' 	{printf("S->hOla\n");}
  	;
O	: O 'o'			{printf("O->Oo\n");}
  	|			{printf ("O->épsilon\n");}
	;
%%
 int main(){
 	yyparse();
	return 0;
 }

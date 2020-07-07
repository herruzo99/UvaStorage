%{
#include <stdio.h>
 int yyerror (char* s) {
	fprintf(stderr, "%s\n", s);
 }
 int yylex();
%}
%%
S	: 'h' O 'l' 'a' '\n' 	{printf("S->hOla\n");}
  	;
O	: O 'o'			{printf("O->Oo\n");}
  	|			{printf ("O->épsilon\n");}
	;
%%
 int yylex() {
	return getchar();
 }
 int main(){
 	yyparse();
	return 0;
 }

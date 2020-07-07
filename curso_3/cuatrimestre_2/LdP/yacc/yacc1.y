%{
#include <stdio.h>
 int yyerror (char* s) {
	fprintf(stderr, "%s\n", s);
 }
 int yylex();
%}
%%
S	: 'h' 'o' 'l' 'a' '\n' {printf("Bien\n");}
  	;
%%
 int yylex() {
	return getchar();
 }
 int main(){
 	yyparse();
	return 0;
 }

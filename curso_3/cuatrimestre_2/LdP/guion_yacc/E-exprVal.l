%{
#include <stdio.h>
#include "y.tab.h"
extern double variables [26];
%}
entero		[1-9][0-9]*|0
fraccion	\.{entero}
exponencial 	[eE][-+]{entero}
flotante 	{entero}{fraccion}?{exponencial}?
print 		[pP][rR][iI][nN][tT]
let		[lL][eE][tT]
variable	[a-z]
%%
[ \t]+	;
{flotante}	{
			yylval.valor = atof (yytext);
			return NUM;
		}
{print}	{ return PRINT;}
{let}		{ return LET;}
{variable}	{

		  yylval.id_variable = (int)yytext[0] - (int)'a';
		  return VARIABLE;
		}
.|\n	{ 
		return yytext[0];

	}


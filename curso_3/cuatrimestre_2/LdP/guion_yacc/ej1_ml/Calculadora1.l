%{
#include <stdio.h>
#include "y.tab.h"
%}
entero	[1-9][0-9]*|0
%%
[ \t]+	;
{entero}	{
			yylval = atoi (yytext);
			return NUM;
		}
.|\n	{ 
		return yytext[0];
	}


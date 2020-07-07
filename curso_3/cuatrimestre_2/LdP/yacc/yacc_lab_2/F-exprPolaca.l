%{
#include <stdio.h>
#include<string.h>
#include "y.tab.h"
%}
entero	[1-9][0-9]*|0
%%
[ \t]+	;
{entero}	{
			strcpy(yylval.literal, yytext);
			return NUM;
		}
.|\n	{ 
		return yytext[0];
	}

